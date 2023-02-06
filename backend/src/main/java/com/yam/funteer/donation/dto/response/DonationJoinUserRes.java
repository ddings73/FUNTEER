package com.yam.funteer.donation.dto.response;

import com.yam.funteer.user.entity.User;

import lombok.Getter;

@Getter
public class DonationJoinUserRes {
	private User user;

	public DonationJoinUserRes(User user){
		this.user=user;
	}
}
