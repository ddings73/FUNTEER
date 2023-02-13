package com.yam.funteer.donation.dto.response;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.yam.funteer.common.code.PostType;
import com.yam.funteer.donation.entity.Donation;

import lombok.Getter;

@Getter
public class DonationBaseRes {
	private Long id;
	private String title;
	private String content;
	private String file;
	private String targetAmount;
	private String currentAmount;
	private PostType postType;
	private LocalDate startDate;
	private LocalDate endDate;

	public DonationBaseRes(Donation entity,String file){
		this.id=entity.getDonationId();
		this.title=entity.getTitle();
		this.content=entity.getContent();
		this.file=file;
		this.targetAmount=entity.getAmount().toString();
		this.currentAmount=entity.getCurrentAmount().toString();
		this.postType=entity.getPostType();
		this.startDate=entity.getStartDate();
		this.endDate=entity.getEndDate();

	}
}

