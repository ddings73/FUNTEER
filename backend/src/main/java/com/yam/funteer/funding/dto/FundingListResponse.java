package com.yam.funteer.funding.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.minidev.json.annotate.JsonIgnore;

import com.yam.funteer.common.code.PostType;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.entity.TargetMoney;
import com.yam.funteer.funding.repository.FundingRepository;
import com.yam.funteer.post.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class FundingListResponse {

	private String title;

	private LocalDate startDate;

	private LocalDate endDate;

	private LocalDateTime postDate;

	private String fundingDescription;

	private Long currentFundingAmount;

	private PostType postType;

	private int amount;

	private String thumbnail;

	public static FundingListResponse from(Funding funding) {

		return FundingListResponse.builder()
			.title(funding.getTitle())
			.startDate(funding.getStartDate())
			.endDate(funding.getEndDate())
			.postDate(funding.getRegDate())
			.thumbnail(funding.getThumbnail())
			.fundingDescription(funding.getFundingDescription())
			.currentFundingAmount(funding.getCurrentFundingAmount())
			.amount(funding.getTargetMoneyList().get(2).getAmount())
			.postType(funding.getPostType())
			.build();

	}
}
