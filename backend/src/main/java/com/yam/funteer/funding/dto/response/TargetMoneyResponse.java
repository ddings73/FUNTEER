package com.yam.funteer.funding.dto.response;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.yam.funteer.common.code.TargetMoneyType;
import com.yam.funteer.funding.entity.TargetMoney;
import com.yam.funteer.funding.entity.TargetMoneyDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TargetMoneyResponse {

	private String amount;
	private TargetMoneyType targetMoneyType;
	private List<TargetMoneyDetailResponse> descriptions;

	public static TargetMoneyResponse from (TargetMoney targetMoney) {

		DecimalFormat decFormat = new DecimalFormat("###,###");
		String str = decFormat.format(targetMoney.getAmount());

		List<TargetMoneyDetailResponse> targetMoneyDetailResponses = new ArrayList<>();

		for (TargetMoneyDetail tmd : targetMoney.getDescriptions()) {
			targetMoneyDetailResponses.add(TargetMoneyDetailResponse.from(tmd));
		}
		return TargetMoneyResponse.builder()
			.targetMoneyType(targetMoney.getTargetMoneyType())
			.descriptions(targetMoneyDetailResponses)
			.amount(str)
			.build();
	}

}
