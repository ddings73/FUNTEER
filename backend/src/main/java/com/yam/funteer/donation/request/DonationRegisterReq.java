package com.yam.funteer.donation.request;

import java.time.LocalDateTime;
import java.util.List;

import com.yam.funteer.code.GroupCode;
import com.yam.funteer.post.entity.Category;
import com.yam.funteer.post.entity.Hashtag;

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
