package com.yam.funteer.donation.service;

import java.util.List;

import com.yam.funteer.common.code.PostType;
import com.yam.funteer.donation.dto.request.DonationJoinReq;
import com.yam.funteer.donation.dto.request.DonationRegisterReq;

import com.yam.funteer.donation.dto.request.DonationModifyReq;
import com.yam.funteer.donation.dto.response.DonationAdminListRes;
import com.yam.funteer.donation.dto.response.DonationBaseRes;
import com.yam.funteer.donation.dto.response.DonationListRes;

import com.yam.funteer.pay.entity.Payment;

public interface DonationService {
	List<DonationListRes> donationGetList(int page,int size);
	List<DonationAdminListRes>donationGetAdminList(int page,int size);
	Payment donationJoin(Long postId, DonationJoinReq donationJoinReq) ;
	DonationBaseRes donationGetDetail(Long postId);
	DonationBaseRes donationGetCurrent();
	DonationBaseRes donationRegister(DonationRegisterReq donationRegisterReq);
	// void donationDelete(Long postId,Long userId) throws DonationNotFoundException;
	void donationStatusModify(Long id, PostType postType);
	DonationBaseRes donationModify(Long postId, DonationModifyReq donationModifyReq);
}
