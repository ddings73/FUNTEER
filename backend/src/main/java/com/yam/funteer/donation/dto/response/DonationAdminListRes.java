package com.yam.funteer.donation.dto.response;

import java.time.LocalDate;

import org.springframework.data.domain.Pageable;

import com.yam.funteer.common.code.PostType;
import com.yam.funteer.donation.entity.Donation;

import lombok.Getter;

@Getter
public class DonationAdminListRes {
	private Long id;
	private String title;
	private String targetAmount;
	private String currentAmount;
	private PostType postType;
	private LocalDate startDate;
	private LocalDate endDate;
	private Pageable pageable;

	public DonationAdminListRes(Donation entity,Pageable pageable){
		this.id=entity.getDonationId();
		this.title=entity.getTitle();
		this.targetAmount=entity.getAmount().toString();
		this.currentAmount=entity.getCurrentAmount().toString();
		this.postType=entity.getPostType();
		this.startDate=entity.getStartDate();
		this.endDate=entity.getEndDate();
		this.pageable=pageable;

	}
}

