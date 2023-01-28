package com.yam.funteer.funding.service;

import java.io.IOException;
import java.util.List;

import com.yam.funteer.funding.dto.FundingCommentRequest;
import com.yam.funteer.funding.dto.FundingDetailResponse;
import com.yam.funteer.funding.dto.FundingListResponse;
import com.yam.funteer.funding.dto.FundingReportRequest;
import com.yam.funteer.funding.dto.FundingReportResponse;
import com.yam.funteer.funding.dto.FundingRequest;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.exception.FundingNotFoundException;

public interface FundingService {
	List<FundingListResponse> findApprovedFunding(String keyword, String category, String hashTag);

	FundingDetailResponse createFunding(FundingRequest data) throws IOException;

	FundingDetailResponse findFundingById(Long id);

	FundingDetailResponse updateFunding(Long fundingId, FundingRequest data) throws Exception;

	void deleteFunding(Long fundingId) throws FundingNotFoundException;

	void createFundingReport(FundingReportRequest data);

	FundingReportResponse findFundingReportById(Long fundingId);

	FundingReportResponse updateFundingReport(Long fundingId, FundingReportResponse data);

	void createFundingComment(FundingCommentRequest data);

	List<FundingListResponse> findAllFunding();
}
