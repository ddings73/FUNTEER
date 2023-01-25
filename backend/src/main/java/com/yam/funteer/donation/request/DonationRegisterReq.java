package com.yam.funteer.donation.request;

import java.util.List;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonationRegisterReq {
	private Long adminId;
	private String content;
	private String title;
	private Integer amount;
	private String category;
	private List<String>hashtags;

}
