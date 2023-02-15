package com.yam.funteer.funding.dto.response;


import com.yam.funteer.funding.entity.Funding;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class FundingListPageResponse {

	private List<FundingListResponse> fundingListResponses;
	private long totalElements;
	private int totalPages;
//	private Long totalFundingCount;

//	private int successFundingCount;

//	private Long inProgressFundingAmount;

	public static FundingListPageResponse of(Page<Funding> fundingPage){
		List<FundingListResponse> responseList = fundingPage.stream().map(FundingListResponse::from).collect(Collectors.toList());
		return new FundingListPageResponse(responseList, fundingPage.getTotalElements(), fundingPage.getTotalPages());
	}

}
