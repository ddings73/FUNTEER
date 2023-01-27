package com.yam.funteer.funding.dto;

import java.time.LocalDateTime;

import net.minidev.json.annotate.JsonIgnore;

import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.post.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FundingListResponse {

	private String title;
	private String content;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private LocalDateTime postDate;

	public static FundingListResponse from(Funding funding) {
		return FundingListResponse.builder()
			.title(funding.getTitle())
			.content(funding.getContent())
			.startDate(funding.getStartDate())
			.endDate(funding.getEndDate())
			.postDate(funding.getRegDate())
			.build();
	}
}
