package com.yam.funteer.funding.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class FundingReportDetailRequest {
	private String amount;
	private String description;

}
