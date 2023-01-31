package com.yam.funteer.donation.dto.request;

import com.yam.funteer.user.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class DonationJoinReq {
	private Long paymentAmount;
}
