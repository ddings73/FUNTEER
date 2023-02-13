package com.yam.funteer.donation.dto.response;

import java.time.LocalDate;

import org.springframework.data.domain.Pageable;

import com.yam.funteer.common.code.PostType;
import com.yam.funteer.donation.entity.Donation;

import lombok.Getter;

@Getter
public class DonationListRes {

	private Long id;
	private String title;
	private String content;
	private String targetAmount;
	private PostType postType;
	private LocalDate startDate;
	private LocalDate endDate;
	private Pageable pageable;


	public DonationListRes(Donation donation,Pageable pageable) {
		this.id = donation.getDonationId();
		this.title = donation.getTitle();
		this.content = donation.getContent();
		this.postType = donation.getPostType();
		this.startDate = donation.getStartDate();
		this.endDate = donation.getEndDate();
		this.targetAmount = donation.getAmount().toString();
		this.pageable=pageable;
	}
}
