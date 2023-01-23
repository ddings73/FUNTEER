package com.yam.funteer.funding.dto;

import java.time.LocalDateTime;

import com.yam.funteer.post.entity.Post;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FundingDetailResponse {

	private Long fundingId;
	private String title;
	private String content;
	private LocalDateTime start;
	private LocalDateTime end;

	public static FundingDetailResponse from(Post funding) {
		return FundingDetailResponse.builder().build();
	}
}
