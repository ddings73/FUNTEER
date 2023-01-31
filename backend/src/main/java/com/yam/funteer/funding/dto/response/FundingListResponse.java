package com.yam.funteer.funding.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.funding.entity.Funding;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FundingListResponse {

	private Long id;

	private String title;

	private LocalDate startDate;

	private LocalDate endDate;

	private LocalDateTime postDate;

	private String fundingDescription;

	private Long currentFundingAmount;

	private PostType postType;

	private int amount;

	private String thumbnail;

	public static FundingListResponse from(Funding funding) {

		return FundingListResponse.builder()
			.id(funding.getId())
			.title(funding.getTitle())
			.startDate(funding.getStartDate())
			.endDate(funding.getEndDate())
			.postDate(funding.getRegDate())
			.thumbnail(funding.getThumbnail())
			.fundingDescription(funding.getFundingDescription())
			.currentFundingAmount(funding.getCurrentFundingAmount())
			.amount(funding.getTargetMoneyList().get(2).getAmount())
			.postType(funding.getPostType())
			.build();

	}
}
