package com.yam.funteer.funding.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FundingListPageResponse {

	private List<FundingListResponse> fundingListResponses;

	private int totalFundingCount;

	private int successFundingCount;

	private Long totalFundingAmount;

}
