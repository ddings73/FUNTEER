package com.yam.funteer.funding.dto.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.yam.funteer.funding.entity.Funding;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FundingRequest {

	@NotNull
	private Long categoryId;

	@NotNull
	private String title;
	@NotNull
	private String content;
	@NotNull
	private String startDate;
	@NotNull
	private String endDate;

	@NotNull
	private String fundingDescription;

	private TargetMoneyRequest targetMoneyLevelOne;
	private TargetMoneyRequest targetMoneyLevelTwo;
	private TargetMoneyRequest targetMoneyLevelThree;
	private String hashtags;


}
