package com.yam.funteer.funding.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.yam.funteer.common.code.PostType;
import com.yam.funteer.common.code.TargetMoneyType;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.entity.TargetMoney;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FundingListResponse {

	private Long id;

	private String title;

	private LocalDate startDate;

	private LocalDate endDate;

	private LocalDateTime postDate;

	private String fundingDescription;

	private Long currentFundingAmount;

	private PostType postType;

	private Long targetAmount;

	private String thumbnail;

	private Long categoryId;

	private HashtagResponse postHashtagList;

	public static FundingListResponse from(Funding funding) {

		Long targetAmount = 0L;

		for (TargetMoney targetMoney : funding.getTargetMoneyList()) {
			if (targetMoney.getTargetMoneyType() == TargetMoneyType.LEVEL_THREE) {
				targetAmount += targetMoney.getAmount();
			}
		}

		FundingListResponse response = FundingListResponse.builder()
			.id(funding.getId())
			.title(funding.getTitle())
			.startDate(funding.getStartDate())
			.endDate(funding.getEndDate())
			.postDate(funding.getRegDate())
			.thumbnail(funding.getThumbnail())
			.fundingDescription(funding.getFundingDescription())
			.currentFundingAmount(funding.getCurrentFundingAmount())
			.targetAmount(targetAmount)
			.postType(funding.getPostType())
			.categoryId(funding.getCategory().getId())
			.build();


		if (funding.getHashtags() != null) {
			response.setPostHashtagList(HashtagResponse.from(funding.getHashtags()));
		}

		// funding.getPostHashtags().ifPresent(postHashtags -> response.setPostHashtagList((HashtagResponse)postHashtags));

		return response;
	}
}
