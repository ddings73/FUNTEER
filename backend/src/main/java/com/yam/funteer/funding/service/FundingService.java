package com.yam.funteer.funding.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yam.funteer.common.code.PostType;
import com.yam.funteer.funding.dto.request.FundingCommentRequest;
import com.yam.funteer.funding.dto.response.FundingDetailResponse;
import com.yam.funteer.funding.dto.response.FundingListPageResponse;
import com.yam.funteer.funding.dto.response.FundingListResponse;
import com.yam.funteer.funding.dto.request.FundingReportRequest;
import com.yam.funteer.funding.dto.response.FundingReportResponse;
import com.yam.funteer.funding.dto.request.FundingRequest;
import com.yam.funteer.funding.dto.request.TakeFundingRequest;

public interface FundingService {
	Page<FundingListResponse> findAllFundingByAdmin(String keyword, PostType postType, Pageable pageable);

	Page<FundingListResponse> findFundingByCategory(Long categoryId, Pageable pageable);

	Page<FundingListResponse> findFundingByKeywordByAdmin(String keyword, Pageable pageable);

	FundingDetailResponse createFunding( FundingRequest data);

	FundingDetailResponse findFundingById(Long id, Pageable pageable);

	FundingDetailResponse updateFunding(Long fundingId, FundingRequest data);

	void deleteFunding(Long fundingId);

	FundingReportResponse createFundingReport(Long fundingId, FundingReportRequest data);

	FundingReportResponse findFundingReportById(Long fundingId);

	FundingReportResponse updateFundingReport(Long fundingId, FundingReportRequest data);

	void createFundingComment(Long fundingId, FundingCommentRequest data);

	void deleteFundingComment(Long commentId);

	FundingListPageResponse findAllFunding(Pageable pageable, PostType postType, Long categoryId, String keyword);

	void takeFunding(Long fundingId, TakeFundingRequest data);

	Page<FundingListResponse> findFundingByKeyword(String keyword, Pageable pageable);

	Page<FundingListResponse> findFundingByHashtag(String hashtag, Pageable pageable);

	int updateHit(Long fundingId, HttpServletRequest request, HttpServletResponse response);


}
