package com.yam.funteer.funding.service;

import java.util.List;

import com.yam.funteer.funding.dto.FundingDetailResponse;
import com.yam.funteer.funding.dto.FundingListResponse;
import com.yam.funteer.funding.dto.FundingRequest;
import com.yam.funteer.post.PostType;
import com.yam.funteer.post.entity.Post;

public interface FundingService {
	List<FundingListResponse> findApprovedFunding(String keyword, String category, String hashTag);

	void createFunding(FundingRequest data);

	FundingDetailResponse findFundingById(Long id);

	FundingDetailResponse updateFunding(Long fundingId, FundingRequest data);
}
