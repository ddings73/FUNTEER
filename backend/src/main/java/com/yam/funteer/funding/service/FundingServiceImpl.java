package com.yam.funteer.funding.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.yam.funteer.common.aws.AwsS3Uploader;
import com.yam.funteer.common.code.TargetMoneyType;
import com.yam.funteer.funding.dto.FundingCommentRequest;
import com.yam.funteer.funding.dto.FundingDetailResponse;
import com.yam.funteer.funding.dto.FundingListResponse;
import com.yam.funteer.funding.dto.FundingReportRequest;
import com.yam.funteer.funding.dto.FundingReportResponse;
import com.yam.funteer.funding.dto.FundingRequest;
import com.yam.funteer.funding.entity.Category;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.entity.TargetMoney;
import com.yam.funteer.funding.exception.FundingNotFoundException;
import com.yam.funteer.funding.repository.FundingRepository;
import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.funding.repository.TargetMoneyRepository;
import com.yam.funteer.post.entity.Hashtag;
import com.yam.funteer.post.entity.PostHashtag;
import com.yam.funteer.post.repository.CategoryRepository;
import com.yam.funteer.post.repository.HashTagRepository;
import com.yam.funteer.post.repository.PostHashtagRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FundingServiceImpl implements FundingService{
	private final CategoryRepository categoryRepository;

	private final FundingRepository fundingRepository;
	private final TargetMoneyRepository targetMoneyRepository;
	private final HashTagRepository hashTagRepository;
	private final AwsS3Uploader awsS3Uploader;

	private final PostHashtagRepository postHashtagRepository;

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
	public FundingDetailResponse createFunding(FundingRequest data) throws IOException {
		// s3 변환
		String thumbnailUrl = awsS3Uploader.upload(data.getThumbnail(), "/thumbnails");
		System.out.println(data.getHashtags());

		// category 들고오기
		Category category = categoryRepository.findById(data.getCategoryId()).orElseThrow();

		// time 변환
		LocalDateTime startDate = LocalDateTime.parse(data.getStartDate(),
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		LocalDateTime endDate = LocalDateTime.parse(data.getEndDate(),
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		// 펀딩 생성
		Funding funding = Funding.builder()
			.title(data.getTitle())
			.category(category)
			.thumbnail(thumbnailUrl)
			.content(data.getContent())
			.startDate(startDate)
			.endDate(endDate)
			.regDate(LocalDateTime.now())
			.hit(0)
			.postGroup(PostGroup.FUNDING)
			.postType(PostType.FUNDING_WAIT)
			.build();

		Funding savedPost = fundingRepository.save(funding);

		addTargetMoney(data, funding);
		List<Hashtag> hashtagList = parseHashTags(data.getHashtags());
		List<Hashtag> hashtags = saveNotExistHashTags(hashtagList);
		addPostHashtags(funding, hashtags);

		return FundingDetailResponse.from(savedPost);

	}

	private void addPostHashtags(Funding funding, List<Hashtag> hashtagList) {
		List<PostHashtag> postHashtagList = new ArrayList<>();
		for (Hashtag hashtag : hashtagList) {
			PostHashtag postHashtag = new PostHashtag(funding, hashtag);
			postHashtagRepository.save(postHashtag);
			postHashtagList.add(postHashtag);
		}

		funding.setHashtags(postHashtagList);
	}

	private void addTargetMoney(FundingRequest data, Funding funding) {
		List<TargetMoney> targetMoneyList = new ArrayList<>();

		TargetMoney targetMoney1 = new TargetMoney(funding, TargetMoneyType.LEVEL_ONE, data.getAmount1(),
			data.getDescription1());
		TargetMoney targetMoney2 = new TargetMoney(funding, TargetMoneyType.LEVEL_TWO, data.getAmount2(),
			data.getDescription2());
		TargetMoney targetMoney3 = new TargetMoney(funding, TargetMoneyType.LEVEL_THREE, data.getAmount3(),
			data.getDescription3());

		targetMoneyRepository.save(targetMoney1);
		targetMoneyRepository.save(targetMoney2);
		targetMoneyRepository.save(targetMoney3);

		targetMoneyList.add(targetMoney1);
		targetMoneyList.add(targetMoney2);
		targetMoneyList.add(targetMoney3);

		funding.setTargetMoneyList(targetMoneyList);
	}

	private List<Hashtag> saveNotExistHashTags(List<Hashtag> hashtagList){
		List<Hashtag> hashtags = new ArrayList<>();
		for(Hashtag hashtag : hashtagList) {
			Optional<Hashtag> oneByName = hashTagRepository.findOneByName(hashtag.getName());
			if (oneByName.isEmpty()) {
				Hashtag save = hashTagRepository.save(hashtag);
				hashtags.add(save);
			}else{
				hashtags.add(oneByName.get());
			}
		};
		return hashtags;

	}
	private List<Hashtag> parseHashTags(String hashtags) {
		String[] split = hashtags.split("#");
		String[] split2 = Arrays.copyOfRange(split, 1, split.length);
		List<Hashtag> collect = Arrays.stream(split2)
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

		LocalDateTime endDate = LocalDateTime.parse(data.getEndDate(),
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		if (funding.getPostType() == PostType.FUNDING_WAIT) {
			funding.update(data);
		} else if (funding.getPostType() == PostType.FUNDING_IN_PROGRESS) {
			funding.setEndDate(endDate);
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
