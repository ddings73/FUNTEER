package com.yam.funteer.donation.service;

import java.util.List;

import com.yam.funteer.donation.entity.Donation;
import com.yam.funteer.donation.exception.DonationNotFoundException;
import com.yam.funteer.donation.dto.request.DonationJoinReq;
import com.yam.funteer.donation.dto.request.DonationRegisterReq;
import com.yam.funteer.pay.entity.Payment;

public interface DonationService {
	List<Donation> donationGetList();
	Payment donationJoin(Long postId, DonationJoinReq donationJoinReq)throws DonationNotFoundException;
	Donation donationGetDetail(Long postId) throws DonationNotFoundException;
	void donationRegister(DonationRegisterReq donationRegisterReq);
	// void donationDelete(Long postId,Long userId) throws DonationNotFoundException;
	void donationModify(Long postId, DonationRegisterReq donationModifyReq) throws DonationNotFoundException;
}
