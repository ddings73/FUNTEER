package com.yam.funteer.funding.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import net.minidev.json.annotate.JsonIgnore;

import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.entity.TargetMoney;
import com.yam.funteer.post.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FundingListResponse {

	private String title;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private LocalDateTime postDate;

	private String fundingDescription;

	private Long currentFundingAmount;


	private int amount;

	private List<TargetMoney> targetMoneyList;

	private String thumbnail;

	public static FundingListResponse from(Funding funding) {

		return FundingListResponse.builder()
			.title(funding.getTitle())
			.startDate(funding.getStartDate())
			.endDate(funding.getEndDate())
			.postDate(funding.getRegDate())
			.thumbnail(funding.getThumbnail())
			.fundingDescription(funding.getFundingDescription())
			.currentFundingAmount(funding.getCurrentFundingAmount())
			.amount(funding.getTargetMoneyList().get(2).getAmount())
			.build();
	}
}
