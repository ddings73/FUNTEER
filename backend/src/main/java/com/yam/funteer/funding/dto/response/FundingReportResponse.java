package com.yam.funteer.funding.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.yam.funteer.funding.entity.Report;
import com.yam.funteer.funding.entity.ReportDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FundingReportResponse {
	private String content;
	private LocalDateTime regDate;

	private List<ReportDetailResponse> reportDetailResponseList;

	public static FundingReportResponse from (Report report) {

		List<ReportDetailResponse> reportDetailResponses = new ArrayList<>();
		for (ReportDetail rd : report.getReportDetails()) {
			reportDetailResponses.add(ReportDetailResponse.from(rd));
		}

		return FundingReportResponse.builder()
			.content(report.getContent())
			.regDate(report.getRegDate())
			.reportDetailResponseList(reportDetailResponses)
			.build();
	}




}
