package com.yam.funteer.funding.dto;

import java.time.LocalDateTime;

import com.yam.funteer.funding.entity.Funding;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@AllArgsConstructor
@Builder
public class FundingDetailResponse {

	private Long fundingId;
	private String title;
	private String content;
	private LocalDateTime start;
	private LocalDateTime end;

	private LocalDateTime postDate;

	// private List<TargetMoney> targetMonies;
	//
	// private List<PostHashtag> postHashtagList;

	public static FundingDetailResponse from(Funding funding) {
		return FundingDetailResponse.builder()
			.fundingId(funding.getId())
			.title(funding.getTitle())
			.content(funding.getContent())
			.start(funding.getStartDate())
			.end(funding.getEndDate())
			.postDate(funding.getRegDate())
			// .targetMonies(funding.getTargetMoney())
			// .postHashtagList(funding.getpostHashTagList)
			.build();
	}

}
