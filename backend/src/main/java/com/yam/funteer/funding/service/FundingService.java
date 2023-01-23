package com.yam.funteer.funding.service;

import java.util.List;

import com.yam.funteer.code.GroupCode;
import com.yam.funteer.funding.dto.FundingDetailResponse;
import com.yam.funteer.funding.dto.FundingListResponse;
import com.yam.funteer.funding.dto.FundingRequest;
import com.yam.funteer.post.entity.Post;

public interface FundingService {
	List<FundingListResponse> findAllFundings(GroupCode groupcode);

	void createFunding(FundingRequest data);

	FundingDetailResponse findFundingById(Long id);

	FundingDetailResponse updateFunding(Long fundingId, FundingRequest data);
}
