package com.yam.funteer.funding.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yam.funteer.code.GroupCode;
import com.yam.funteer.funding.dto.FundingDetailResponse;
import com.yam.funteer.funding.dto.FundingListResponse;
import com.yam.funteer.funding.dto.FundingRequest;
import com.yam.funteer.member.entity.Member;
import com.yam.funteer.post.entity.Post;
import com.yam.funteer.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FundingServiceImpl implements FundingService{

	private final PostRepository postRepository;

	@Override
	public List<FundingListResponse> findAllFundings(GroupCode groupcode) {
		return postRepository.findAllByGroupCode(groupcode).stream().map(post -> FundingListResponse.from(post)).
			collect(Collectors.toList());
	}

	@Override
	public void createFunding(FundingRequest data) {

		Member member;

		Post funding = Post.builder()
			.build();

		postRepository.save(funding);
	}

	@Override
	public FundingDetailResponse updateFunding(Long fundingId, FundingRequest data) {
		Post funding = postRepository.findById(fundingId).orElseThrow(() -> new NullPointerException());
		return FundingDetailResponse.from(funding);
	}

}
