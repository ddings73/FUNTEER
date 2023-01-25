package com.yam.funteer.funding.dto;

import com.yam.funteer.post.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FundingListResponse {

	public static FundingListResponse from(Post post) {
		return FundingListResponse.builder()
			.build();
	}
}
