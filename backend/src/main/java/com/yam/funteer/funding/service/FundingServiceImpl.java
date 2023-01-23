package com.yam.funteer.funding.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.yam.funteer.funding.dto.FundingDetailResponse;
import com.yam.funteer.funding.dto.FundingListResponse;
import com.yam.funteer.funding.dto.FundingRequest;
import com.yam.funteer.post.PostType;
import com.yam.funteer.post.TargetMoneyType;
import com.yam.funteer.post.entity.Post;
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
	public void createFunding(FundingRequest data) {
		// s3 변환
		String thumbnailUrl = "";

		// 첨부파일 여러개일때

		Post funding = Post.builder()
			.title(data.getTitle())
			.thumbnail(thumbnailUrl)
			.content(data.getContent())
			.start(data.getStartDate())
			.end(data.getEndDate())
			.hit(0L)
			.build();

		Post savedPost = postRepository.save(funding);

		TargetMoney targetMoney1 = TargetMoney.builder()
			.targetMoneyID(new TargetMoneyID(TargetMoneyType.C01, savedPost.getId()))
			.amount(data.getAmount1())
			.description(data.getDescription1())
			.build();

		TargetMoney targetMoney2 = TargetMoney.builder()
			.targetMoneyID(new TargetMoneyID(TargetMoneyType.C01, savedPost.getId()))
			.amount(data.getAmount1())
			.description(data.getDescription1())
			.build();

		TargetMoney targetMoney3 = TargetMoney.builder()
			.targetMoneyID(new TargetMoneyID(TargetMoneyType.C01, savedPost.getId()))
			.amount(data.getAmount1())
			.description(data.getDescription1())
			.build();

		targetMoneyRepository.save(targetMoney1);
		targetMoneyRepository.save(targetMoney2);
		targetMoneyRepository.save(targetMoney3);

		funding.getTargetMoney().add(targetMoney1);
		funding.getTargetMoney().add(targetMoney2);
		funding.getTargetMoney().add(targetMoney3);
	}

	@Override
	public FundingDetailResponse findFundingById(Long id) {
		return null;
	}

	@Override
	public FundingDetailResponse updateFunding(Long fundingId, FundingRequest data) {
		Post funding = postRepository.findById(fundingId).orElseThrow(() -> new NullPointerException());
		return FundingDetailResponse.from(funding);
	}

}
