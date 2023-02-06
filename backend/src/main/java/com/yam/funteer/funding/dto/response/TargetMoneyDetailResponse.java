package com.yam.funteer.funding.dto.response;

import com.yam.funteer.funding.entity.ReportDetail;
import com.yam.funteer.funding.entity.TargetMoneyDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TargetMoneyDetailResponse {

	private String description;

	public static TargetMoneyDetailResponse from(TargetMoneyDetail targetMoneyDetail) {
		return new TargetMoneyDetailResponse(targetMoneyDetail.getDescription());
	}
}
