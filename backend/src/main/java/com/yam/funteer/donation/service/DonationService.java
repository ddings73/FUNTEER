package com.yam.funteer.donation.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yam.funteer.donation.dto.request.DonationRegisterReq;
import com.yam.funteer.donation.dto.response.DonationBaseRes;
import com.yam.funteer.donation.dto.response.DonationListRes;
import com.yam.funteer.donation.exception.DonationNotFoundException;
import com.yam.funteer.donation.dto.request.DonationJoinReq;

import com.yam.funteer.pay.entity.Payment;

public interface DonationService {
	List<DonationListRes> donationGetList();
	Payment donationJoin(Long postId, DonationJoinReq donationJoinReq)throws DonationNotFoundException;
	DonationBaseRes donationGetDetail(Long postId) throws DonationNotFoundException;
	DonationBaseRes donationRegister(DonationRegisterReq donationRegisterReq, List<MultipartFile>files) throws IOException;
	// void donationDelete(Long postId,Long userId) throws DonationNotFoundException;
	DonationBaseRes donationModify(Long postId, DonationRegisterReq donationModifyReq,List<MultipartFile>files) throws
		DonationNotFoundException,
		IOException;
}
