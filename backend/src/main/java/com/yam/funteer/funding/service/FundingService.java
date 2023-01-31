package com.yam.funteer.funding.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yam.funteer.funding.dto.request.FundingCommentRequest;
import com.yam.funteer.funding.dto.response.FundingDetailResponse;
import com.yam.funteer.funding.dto.response.FundingListPageResponse;
import com.yam.funteer.funding.dto.response.FundingListResponse;
import com.yam.funteer.funding.dto.request.FundingReportRequest;
import com.yam.funteer.funding.dto.response.FundingReportResponse;
import com.yam.funteer.funding.dto.request.FundingRequest;
import com.yam.funteer.funding.dto.request.RejectReasonRequest;
import com.yam.funteer.funding.dto.request.TakeFundingRequest;
import com.yam.funteer.funding.exception.CommentNotFoundException;
import com.yam.funteer.funding.exception.FundingNotFoundException;

public interface FundingService {
	List<FundingListResponse> findFundingByCategory(Long categoryId);

	FundingDetailResponse createFunding(MultipartFile thumbnail, FundingRequest data) throws IOException;

	FundingDetailResponse findFundingById(Long id);

	FundingDetailResponse updateFunding(Long fundingId, MultipartFile thumbnail, FundingRequest data) throws Exception;

	void deleteFunding(Long fundingId) throws FundingNotFoundException;

	void createFundingReport(Long fundingId, FundingReportRequest data);

	FundingReportResponse findFundingReportById(Long fundingId);

	FundingReportResponse updateFundingReport(Long fundingId, FundingReportResponse data);

	void createFundingComment(Long fundingId, FundingCommentRequest data);

	void deleteFundingComment(Long commentId) throws CommentNotFoundException;

	FundingListPageResponse findAllFunding();

	void takeFunding(Long fundingId, TakeFundingRequest data);

	List<FundingListResponse> findFundingByKeyword(String keyword);

	List<FundingListResponse> findFundingByHashtag(String hashtag);

	void acceptFunding(Long fundingId);

	void rejectFunding(Long fundingId, RejectReasonRequest data) throws Exception;

	void acceptReport(Long fundingId);

	void rejectReport(Long fundingId, RejectReasonRequest data) throws Exception;
}
