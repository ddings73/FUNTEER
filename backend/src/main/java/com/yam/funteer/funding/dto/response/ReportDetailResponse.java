package com.yam.funteer.funding.dto.response;

import java.text.DecimalFormat;

import com.yam.funteer.funding.entity.ReportDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class ReportDetailResponse {

	private String amount;
	private String description;

	public static ReportDetailResponse from(ReportDetail reportDetail) {
		DecimalFormat decFormat = new DecimalFormat("###,###");
		String str = decFormat.format(reportDetail.getAmount());

		return new ReportDetailResponse(str, reportDetail.getDescription());
	}
}
