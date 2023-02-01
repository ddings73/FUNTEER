package com.yam.funteer.donation.dto.response;

import com.yam.funteer.common.code.PostType;
import com.yam.funteer.donation.entity.Donation;

import lombok.Getter;

@Getter
public class DonationListRes {
	private Long id;
	private String title;
	private PostType postType;

	public DonationListRes(Donation donation){
		this.id=donation.getId();
		this.title=donation.getTitle();
		this.postType=donation.getPostType();
	}
}
