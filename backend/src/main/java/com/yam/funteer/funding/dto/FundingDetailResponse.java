package com.yam.funteer.funding.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.entity.TargetMoney;

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

	private String category;

	private LocalDateTime start;
	private LocalDateTime end;

	private LocalDateTime postDate;

	private List<TargetMoneyResponse> targetMonies;

	private HashtagResponse postHashtagList;

	private String thumbnail;

	public static FundingDetailResponse from(Funding funding) {
		List<TargetMoneyResponse> targetMoneyResponses = new ArrayList<>();
		for (TargetMoney tm : funding.getTargetMoneyList()) {
			targetMoneyResponses.add(TargetMoneyResponse.from(tm));
		}

		return FundingDetailResponse.builder()
			.fundingId(funding.getId())
			.category(funding.getCategory().getName())
			.title(funding.getTitle())
			.content(funding.getContent())
			.start(funding.getStartDate())
			.end(funding.getEndDate())
			.postDate(funding.getRegDate())
			.targetMonies(targetMoneyResponses)
			.postHashtagList(HashtagResponse.from(funding.getHashtags()))
			.thumbnail(funding.getThumbnail())
			.build();
	}

}
