package com.yam.funteer.donation.request;

import java.util.List;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonationRegisterReq {
	private Long memberId;
	private String content;
	private String title;
	private Long amount;
}
