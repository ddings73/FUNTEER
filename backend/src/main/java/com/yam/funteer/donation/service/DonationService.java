package com.yam.funteer.donation.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yam.funteer.donation.dto.request.DonationRegisterReq;
import com.yam.funteer.donation.dto.response.DonationBaseRes;
import com.yam.funteer.donation.dto.response.DonationListRes;
import com.yam.funteer.donation.exception.DonationNotFoundException;
import com.yam.funteer.donation.dto.request.DonationJoinReq;

import com.yam.funteer.donation.exception.DonationPayException;
import com.yam.funteer.pay.entity.Payment;

public interface DonationService {
	List<DonationListRes> donationGetList();
	Payment donationJoin(Long postId, DonationJoinReq donationJoinReq) ;
	DonationBaseRes donationGetDetail(Long postId);
	DonationBaseRes donationRegister(DonationRegisterReq donationRegisterReq, List<MultipartFile>files);
	// void donationDelete(Long postId,Long userId) throws DonationNotFoundException;
	DonationBaseRes donationModify(Long postId, DonationRegisterReq donationModifyReq,List<MultipartFile>files);
}
