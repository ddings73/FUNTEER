package com.yam.funteer.donation.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonationGetListReq {
	private String keyword;
	private String category;
	private String hashtag;
}
