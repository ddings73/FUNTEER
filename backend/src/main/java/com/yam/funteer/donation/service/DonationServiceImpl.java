package com.yam.funteer.donation.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.common.code.UserType;
import com.yam.funteer.donation.entity.Donation;
import com.yam.funteer.donation.exception.DonationNotFoundException;
import com.yam.funteer.donation.repository.DonationRepository;
import com.yam.funteer.donation.dto.request.DonationJoinReq;
import com.yam.funteer.donation.dto.request.DonationRegisterReq;
import com.yam.funteer.exception.UserNotFoundException;

import com.yam.funteer.pay.entity.Payment;
import com.yam.funteer.pay.repository.PaymentRepository;


;
import com.yam.funteer.user.entity.Member;
import com.yam.funteer.user.entity.User;
import com.yam.funteer.user.repository.MemberRepository;
import com.yam.funteer.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService{

	private final PaymentRepository paymentRepository;
	private final DonationRepository donationRepository;
	private final UserRepository userRepository;
	private final MemberRepository memberRepository;

	public List<Donation> donationGetList() {
		return donationRepository.findAllByPostGroup(PostGroup
			.DONATION);
	}

	public Donation donationGetDetail(Long postId) throws DonationNotFoundException {
		Donation donation=donationRepository.findById(postId).orElseThrow(()->new DonationNotFoundException("찾으시는 게시물이 존재하지 않습니다."));
		return donation;
	}

	public Payment donationJoin(Long postId, DonationJoinReq donationJoinReq)throws DonationNotFoundException {
		Donation donation=donationRepository.findById(postId).orElseThrow(()->new DonationNotFoundException("찾으시는 게시물이 존재하지 않습니다."));
		User user=userRepository.findById(donationJoinReq.getUserId()).orElseThrow(()->new UserNotFoundException());
		Payment payment=Payment.builder()
			.user(user)
			.amount(donationJoinReq.getPaymentAmount())
			.post(donation)
			.payDate(LocalDateTime.now())
			.build();

		paymentRepository.save(payment);

		return payment;
	}

	public void donationRegister(DonationRegisterReq donationRegisterReq) {
		User user=userRepository.findById(donationRegisterReq.getUserId()).orElseThrow(()->new UserNotFoundException());
		if(user.getUserType().equals(UserType.ADMIN)) {
			Donation donation = Donation.builder()
				.content(donationRegisterReq.getContent())
				.title(donationRegisterReq.getTitle())
				.regDate(LocalDateTime.now())
				.hit(0)
				.amount(donationRegisterReq.getAmount())
				.postGroup(PostGroup.DONATION)
				.postType(PostType.DONATION_ACTIVE)
				.build();

			donationRepository.save(donation);
		}else throw new IllegalArgumentException("접근 권한이 없습니다.");
	}


	public void donationDelete(Long postId,Long userId) throws DonationNotFoundException{
		User user=userRepository.findById(userId).orElseThrow(()->new UserNotFoundException());
		if(user.getUserType().equals(UserType.ADMIN)) {
			Donation donation = donationRepository.findById(postId).orElseThrow(() -> new DonationNotFoundException());
			donationRepository.delete(donation);
		}else throw new IllegalArgumentException();
	}


	public void donationModify(Long postId, DonationRegisterReq donationModifyReq) throws DonationNotFoundException {

		donationRepository.findById(postId).orElseThrow(() -> new DonationNotFoundException("찾으시는 게시물이 없습니다."));

		User user = userRepository.findById(donationModifyReq.getUserId())
			.orElseThrow(() -> new UserNotFoundException());
		if (user.getUserType().equals(UserType.ADMIN)) {
			Donation donation = Donation.builder()
				.id(postId)
				.amount(donationModifyReq.getAmount())
				.content(donationModifyReq.getContent())
				.title(donationModifyReq.getTitle())
				.postGroup(PostGroup.DONATION)
				.postType(PostType.DONATION_ACTIVE)
				.regDate(LocalDateTime.now())
				.build();
			donationRepository.save(donation);
		}else throw new IllegalArgumentException("접근 권한이 없습니다.");
	}
}

