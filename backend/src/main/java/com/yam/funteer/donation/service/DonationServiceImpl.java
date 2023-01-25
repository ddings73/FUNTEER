package com.yam.funteer.donation.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yam.funteer.donation.entity.Donation;
import com.yam.funteer.donation.repository.DonationRepository;
import com.yam.funteer.donation.request.DonationGetListReq;
import com.yam.funteer.donation.request.DonationJoinReq;
import com.yam.funteer.donation.request.DonationRegisterReq;
import com.yam.funteer.pay.entity.Payment;
import com.yam.funteer.pay.repository.PaymentRepository;

import com.yam.funteer.post.PostGroup;
import com.yam.funteer.post.PostType;
import com.yam.funteer.post.entity.Post;
import com.yam.funteer.post.repository.PostRepository;
import com.yam.funteer.user.UserType;
import com.yam.funteer.user.entity.Member;
import com.yam.funteer.user.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService{

	private final PaymentRepository paymentRepository;
	private final MemberRepository memberRepository;
	private final DonationRepository donationRepository;
	private final PostRepository postRepository;

	public List<Donation> donationGetList(DonationGetListReq donationGetListReq) {
		return donationRepository.findAll();
	}

	public Donation donationGetDetail(Long postId) {

		return donationRepository.findById(postId).get();
	}

	public Payment donationJoin(Long postId, DonationJoinReq donationJoinReq) {
		Donation donation=donationRepository.findById(postId).get();
		Member member=memberRepository.findById(donationJoinReq.getMemberId()).get();

		Payment payment=Payment.builder()
			.amount(donationJoinReq.getPaymentAmount())
			.member(member)
			.post(donation)
			.payDate(LocalDateTime.now())
			.build();

		paymentRepository.save(payment);

		return payment;

	}

	//
	public Donation donationRegister(DonationRegisterReq donationRegisterReq) {

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

		return donation;
	}

	//
	public Boolean donationDelete(Long postId, Long memberId) {

		if (donationRepository.findById(postId).isPresent()
			&& memberRepository.findById(memberId).get().getStatus() == UserType.ADMIN) {
			Donation donation = donationRepository.findById(postId).get();
			donationRepository.delete(donation);
			return true;
		}
		return false;
	}

	//
	public Boolean donationModify(Long postId, DonationRegisterReq donationModifyReq) {

		if (donationRepository.findById(postId).isPresent()
			&& memberRepository.findById(donationModifyReq.getMemberId()).get().getStatus() == UserType.ADMIN) {
			Donation donation = donationRepository.findById(postId).get();
			donation.builder()
				.amount(donationModifyReq.getAmount())
				.content(donationModifyReq.getContent())
				.title(donationModifyReq.getTitle())
				.build();
			donationRepository.save(donation);
			return true;
		}
		return false;
	}

}
