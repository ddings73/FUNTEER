package com.yam.funteer.donation.dto.response;

import lombok.Getter;

@Getter
public class DonationListRes {
	private Long id;
	private String title;

	public DonationListRes(Long id,String title){
		this.id=id;
		this.title=title;
	}
}
