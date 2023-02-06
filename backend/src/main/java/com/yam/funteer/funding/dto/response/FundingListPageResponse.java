package com.yam.funteer.funding.dto.response;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FundingListPageResponse {

	private Page<FundingListResponse> fundingListResponses;

	private Long totalFundingCount;

	private int successFundingCount;

	private Long totalFundingAmount;

}
