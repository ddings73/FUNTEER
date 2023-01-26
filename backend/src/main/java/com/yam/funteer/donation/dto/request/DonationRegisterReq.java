package com.yam.funteer.donation.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonationRegisterReq {
	private Long userId;
	private String content;
	private String title;
	private Long amount;
}
