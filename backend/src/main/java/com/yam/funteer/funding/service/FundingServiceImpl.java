package com.yam.funteer.funding.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.yam.funteer.funding.dto.FundingCommentRequest;
import com.yam.funteer.funding.dto.FundingDetailResponse;
import com.yam.funteer.funding.dto.FundingListResponse;
import com.yam.funteer.funding.dto.FundingReportRequest;
import com.yam.funteer.funding.dto.FundingReportResponse;
import com.yam.funteer.funding.dto.FundingRequest;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.exception.FundingNotFoundException;
import com.yam.funteer.funding.repository.FundingRepository;
import com.yam.funteer.post.PostGroup;
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.post.entity.Hashtag;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FundingServiceImpl implements FundingService{

	private final FundingRepository fundingRepository;

	@Override
	public List<FundingListResponse> findApprovedFunding(String keyword, String category, String hashTag) {

		return null;
	}

	@Override
	public List<FundingListResponse> findAllFunding() {
		List<FundingListResponse> collect = fundingRepository.findAll().stream().map(m -> FundingListResponse.from(m)).collect(Collectors.toList());
		return collect;
	}


	@Override
	public Funding createFunding(FundingRequest data) {
		// s3 변환
		// String thumbnailUrl = "";


		// 펀딩 생성
		Funding funding = Funding.builder()
			.title(data.getTitle())
			// .thumbnail(data.getThumbnail())
			.content(data.getContent())
			.startDate(data.getStartDate())
			.endDate(data.getEndDate())
			.regDate(data.getPostDate())
			.hit(0)
			.postGroup(PostGroup.FUNDING)
			.postType(PostType.FUNDING_WAIT)
			.build();

		// List<Hashtag> hashtags = parseHashTags(data.getHashtags());
		//
		// List<PostHashtag> postHashtagList = hashtags.stream()
		// 	.map(hashtag -> new PostHashtag(funding, hashtag)).collect(Collectors.toList());
		//
		// funding.setHashtags(postHashtagList);

		Funding savedPost = fundingRepository.save(funding);

		return savedPost;

	}

	private List<Hashtag> parseHashTags(String hashtags) {
		String[] split = hashtags.split("#");
		List<Hashtag> collect = Arrays.stream(split)
			.map(m -> new Hashtag(m)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public FundingDetailResponse findFundingById(Long id) {
		Funding funding = fundingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
		FundingDetailResponse fundingDetailResponse = FundingDetailResponse.from(funding);
		return fundingDetailResponse;
	}

	@Override
	public FundingDetailResponse updateFunding(Long fundingId, FundingRequest data) throws Exception {
		Funding funding = fundingRepository.findById(fundingId).orElseThrow(() -> new NullPointerException());
		if (funding.getPostType() == PostType.FUNDING_WAIT) {
			funding.update(data);
		} else if (funding.getPostType() == PostType.FUNDING_IN_PROGRESS) {
			funding.setEnd(data.getEndDate());
		} else {
			throw new Exception("no");
		}
		return FundingDetailResponse.from(funding);
	}

	@Override
	public void deleteFunding(Long fundingId) throws FundingNotFoundException {
		Funding funding = fundingRepository.findById(fundingId).orElseThrow(() -> new FundingNotFoundException());
		fundingRepository.delete(funding);
	}

	@Override
	public void createFundingReport(FundingReportRequest data) {

	}

	@Override
	public FundingReportResponse findFundingReportById(Long fundingId) {
		return null;
	}

	@Override
	public FundingReportResponse updateFundingReport(Long fundingId, FundingReportResponse data) {
		return null;
	}

	@Override
	public void createFundingComment(FundingCommentRequest data) {

	}

}
