package com.yam.funteer.donation.dto.response;

import java.util.List;

import com.yam.funteer.common.code.PostType;
import com.yam.funteer.donation.entity.Donation;

import lombok.Getter;

@Getter
public class DonationBaseRes {
	private Long id;
	private String title;
	private String content;
	private List<String>files;
	private Long targetAmount;
	private Long currentAmount;
	private PostType postType;

	public DonationBaseRes(Donation entity, Long currentAmount,List<String>files){
		this.id=entity.getId();
		this.title=entity.getTitle();
		this.content=entity.getContent();
		this.files=files;
		this.targetAmount=entity.getAmount();
		this.currentAmount=currentAmount;
		this.postType=entity.getPostType();
	}
}

