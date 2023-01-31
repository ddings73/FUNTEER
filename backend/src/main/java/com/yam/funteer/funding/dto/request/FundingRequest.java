package com.yam.funteer.funding.dto.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private String  startDate;
	@NotNull
	private String endDate;

	@NotNull
	private String fundingDescription;

	@NotNull
	private int amount1;
	@NotNull
	private String description1;

	@NotNull
	private int amount2;
	@NotNull
	private String description2;

	@NotNull
	private int amount3;
	@NotNull
	private String description3;

	private String hashtags;

}
