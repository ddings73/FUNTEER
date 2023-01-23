package com.yam.funteer.funding.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FundingRequest {

	private Long FundingId;
	private String title;
	private String content;
	private LocalDateTime startDate;
	private LocalDateTime endDate;

}
