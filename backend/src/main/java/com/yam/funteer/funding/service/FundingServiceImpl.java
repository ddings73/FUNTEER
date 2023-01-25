package com.yam.funteer.funding.service;

import java.lang.annotation.Target;
import java.util.ArrayList;
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
import com.yam.funteer.funding.exception.PostNotFoundException;
import com.yam.funteer.post.PostType;
import com.yam.funteer.post.TargetMoneyType;
import com.yam.funteer.post.entity.Hashtag;
import com.yam.funteer.post.entity.Post;
import com.yam.funteer.post.entity.PostHashtag;
import com.yam.funteer.post.entity.TargetMoney;
import com.yam.funteer.post.entity.TargetMoneyID;
import com.yam.funteer.post.repository.PostRepository;
import com.yam.funteer.post.repository.TargetMoneyRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FundingServiceImpl implements FundingService{

	private final PostRepository postRepository;
	private final TargetMoneyRepository targetMoneyRepository;

	@Override
	public List<FundingListResponse> findApprovedFunding(String keyword, String category, String hashTag) {

		return null;
	}

	@Override
	public Post createFunding(FundingRequest data) {
		// s3 변환
		String thumbnailUrl = "";


		// 첨부파일 여러개일때
		Post funding = Post.builder()
			.title(data.getTitle())
			.thumbnail(thumbnailUrl)
			.content(data.getContent())
			.start(data.getStartDate())
			.end(data.getEndDate())
			.date(data.getPostDate())
			.hit(0L)
			.postType(PostType.D11)
			.build();

		// List<Hashtag> hashtags = parseHashTags(data.getHashtags());
		//
		// List<PostHashtag> postHashtagList = hashtags.stream()
		// 	.map(hashtag -> new PostHashtag(funding, hashtag)).collect(Collectors.toList());
		// funding.setHashtags(postHashtagList);

		Post savedPost = postRepository.save(funding);

		TargetMoney targetMoney1 = TargetMoney.builder()
			.targetMoneyID(new TargetMoneyID(TargetMoneyType.C01, savedPost.getId()))
			.amount(data.getAmount1())
			.description(data.getDescription1())
			.build();

		TargetMoney targetMoney2 = TargetMoney.builder()
			.targetMoneyID(new TargetMoneyID(TargetMoneyType.C02, savedPost.getId()))
			.amount(data.getAmount1())
			.description(data.getDescription1())
			.build();

		TargetMoney targetMoney3 = TargetMoney.builder()
			.targetMoneyID(new TargetMoneyID(TargetMoneyType.C03, savedPost.getId()))
			.amount(data.getAmount1())
			.description(data.getDescription1())
			.build();

		//// 복합키 사용 안했을 때
		// TargetMoney targetMoney1 = TargetMoney.builder()
		// 	.targetMoneyType(TargetMoneyType.C01)
		// 	.post(savedPost)
		// 	.amount(data.getAmount1())
		// 	.description(data.getDescription1())
		// 	.build();

		targetMoneyRepository.save(targetMoney1);
		targetMoneyRepository.save(targetMoney2);
		targetMoneyRepository.save(targetMoney3);

		List<TargetMoney> targetMonies = new ArrayList<>();
		targetMonies.add(targetMoney1);
		targetMonies.add(targetMoney2);
		targetMonies.add(targetMoney3);

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
		Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
		FundingDetailResponse fundingDetailResponse = FundingDetailResponse.from(post);
		return fundingDetailResponse;

	}

	@Override
	public FundingDetailResponse updateFunding(Long fundingId, FundingRequest data) throws Exception {
		Post funding = postRepository.findById(fundingId).orElseThrow(() -> new NullPointerException());
		if (funding.getPostType() == PostType.D12) {
			funding.update(data);
		} else if (funding.getPostType() == PostType.D14) {
			funding.setEnd(data.getEndDate());
		} else {
			throw new Exception("no");
		}
		return FundingDetailResponse.from(funding);
	}

	@Override
	public void deleteFunding(Long fundingId) throws PostNotFoundException {
		Post funding = postRepository.findById(fundingId).orElseThrow(() -> new PostNotFoundException());
		postRepository.delete(funding);
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
