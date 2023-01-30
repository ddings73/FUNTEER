package com.yam.funteer.donation.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.attach.entity.PostAttach;
import com.yam.funteer.attach.repository.AttachRepository;
import com.yam.funteer.attach.repository.PostAttachRepository;
import com.yam.funteer.common.aws.AwsS3Uploader;
import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.common.code.UserType;
import com.yam.funteer.common.security.SecurityUtil;
import com.yam.funteer.donation.dto.request.DonationRegisterReq;
import com.yam.funteer.donation.dto.response.DonationBaseRes;
import com.yam.funteer.donation.dto.response.DonationListRes;
import com.yam.funteer.donation.entity.Donation;
import com.yam.funteer.donation.exception.DonationNotFoundException;
import com.yam.funteer.donation.repository.DonationRepository;
import com.yam.funteer.donation.dto.request.DonationJoinReq;

import com.yam.funteer.exception.UserNotFoundException;

import com.yam.funteer.pay.entity.Payment;
import com.yam.funteer.pay.repository.PaymentRepository;

import com.yam.funteer.user.entity.User;
import com.yam.funteer.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService{

	private final PaymentRepository paymentRepository;
	private final DonationRepository donationRepository;
	private final UserRepository userRepository;
	private final AwsS3Uploader awsS3Uploader;
	private final AttachRepository attachRepository;
	private final PostAttachRepository postAttachRepository;

	public List<DonationListRes> donationGetList() {
		List<Donation>donations=donationRepository.findAllByPostGroup(PostGroup.DONATION);
		List<DonationListRes>list=new ArrayList<>();
		for(Donation donation:donations){
			DonationListRes donationListRes=new DonationListRes(donation.getId(),donation.getTitle());
			list.add(donationListRes);
		}
		return list;
	}

	public DonationBaseRes donationGetDetail(Long postId) throws DonationNotFoundException {
		Donation donation=donationRepository.findById(postId).orElseThrow(()->new DonationNotFoundException("찾으시는 게시물이 존재하지 않습니다."));

		Long currentAmount=Long.valueOf(0);

		List<Payment>paymentList=paymentRepository.findAllByPost(donation);

		for(Payment payment:paymentList){
			currentAmount+=payment.getAmount();
		}
		List<String>attachList=new ArrayList<>();
		if(postAttachRepository.findAllByPost(donation).size()>0){
			List<PostAttach>postAttachList=postAttachRepository.findAllByPost(donation);

			if(postAttachList.size()>0) {
				for (PostAttach postAttach : postAttachList) {
					attachList.add(postAttach.getAttach().getPath());
				}
			}
		}
		return new DonationBaseRes(donation,currentAmount,attachList);
	}

	public Payment donationJoin(Long postId, DonationJoinReq donationJoinReq)throws DonationNotFoundException {
		Donation donation=donationRepository.findById(postId).orElseThrow(()->new DonationNotFoundException("찾으시는 게시물이 존재하지 않습니다."));
		User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());
		Payment payment=Payment.builder()
			.user(user)
			.amount(donationJoinReq.getPaymentAmount())
			.post(donation)
			.payDate(LocalDateTime.now())
			.build();

		paymentRepository.save(payment);

		return payment;
	}

	public DonationBaseRes donationRegister(DonationRegisterReq donationRegisterReq,List<MultipartFile>files) throws IOException {
		User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());

		if(user.getUserType().equals(UserType.ADMIN)) {
			Donation donation=donationRepository.save(donationRegisterReq.toEntity());
			List<String>attachList=new ArrayList<>();
			for(MultipartFile file:files){
				String fileUrl = awsS3Uploader.upload(file,"/donation");
				Attach attach=donationRegisterReq.toAttachEntity(fileUrl,file.getOriginalFilename());
				PostAttach postAttach=PostAttach.builder()
					.attach(attach)
					.post(donation)
					.build();
				attachList.add(fileUrl);
				attachRepository.save(attach);
				postAttachRepository.save(postAttach);
			}
			return new DonationBaseRes(donation,Long.valueOf(0),attachList);
		}else throw new IllegalArgumentException("접근 권한이 없습니다.");
	}


	public void donationDelete(Long postId) throws DonationNotFoundException{
		User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());
		if(user.getUserType().equals(UserType.ADMIN)) {
			Donation donation = donationRepository.findById(postId).orElseThrow(() -> new DonationNotFoundException());
			donationRepository.delete(donation);
		}else throw new IllegalArgumentException();
	}


	public DonationBaseRes donationModify(Long postId, DonationRegisterReq donationModifyReq,List<MultipartFile>files) throws
		DonationNotFoundException, IOException {
		donationRepository.findById(postId).orElseThrow(() -> new DonationNotFoundException("찾으시는 게시물이 없습니다."));

		User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());
		if(user.getUserType().equals(UserType.ADMIN)) {
			Donation donation=donationRepository.save(donationModifyReq.toEntity(postId));

			List<PostAttach>postAttachList=postAttachRepository.findAllByPost(donation);
			for(PostAttach postAttach:postAttachList){
				awsS3Uploader.delete("/donation/",postAttach.getAttach().getPath());
				postAttachRepository.deleteById(postAttach.getId());
				attachRepository.deleteById(postAttach.getAttach().getId());
			}

			List<String>attachList=new ArrayList<>();
			for(MultipartFile file:files){
				String fileUrl = awsS3Uploader.upload(file,"/donation");
				Attach attach=donationModifyReq.toAttachEntity(fileUrl,file.getOriginalFilename());
				PostAttach postAttach=PostAttach.builder()
					.attach(attach)
					.post(donation)
					.build();
				attachList.add(fileUrl);
				attachRepository.save(attach);
				postAttachRepository.save(postAttach);
			}
			return new DonationBaseRes(donation,Long.valueOf(0),attachList);
		}else throw new IllegalArgumentException("접근 권한이 없습니다.");
	}
}

