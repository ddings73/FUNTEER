package com.yam.funteer.funding.dto.response;

import com.yam.funteer.funding.entity.ReportDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class ReportDetailResponse {

	private Long amount;
	private String description;

	public static ReportDetailResponse from(ReportDetail reportDetail) {
		return new ReportDetailResponse(reportDetail.getAmount(), reportDetail.getDescription());
	}
}
