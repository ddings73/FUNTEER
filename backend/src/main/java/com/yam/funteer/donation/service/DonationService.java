package com.yam.funteer.donation.service;

import java.util.List;

import com.yam.funteer.donation.entity.Donation;
import com.yam.funteer.donation.request.DonationGetListReq;
import com.yam.funteer.donation.request.DonationJoinReq;
import com.yam.funteer.donation.request.DonationRegisterReq;
import com.yam.funteer.pay.entity.Payment;

public interface DonationService {
	List<Donation> donationGetList(DonationGetListReq donationGetListReq);
	Payment donationJoin( Long postId, DonationJoinReq donationJoinReq);
	Donation donationGetDetail(Long postId);
	Donation donationRegister(DonationRegisterReq donationRegisterReq);
	Boolean donationDelete(Long postId, Long memberId);
	Boolean donationModify(Long postId, DonationRegisterReq donationModifyReq);
}
