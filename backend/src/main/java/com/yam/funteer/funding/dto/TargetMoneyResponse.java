package com.yam.funteer.funding.dto;

import com.yam.funteer.common.code.TargetMoneyType;
import com.yam.funteer.funding.entity.TargetMoney;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class TargetMoneyResponse {

	private int amount;
	private TargetMoneyType targetMoneyType;
	private String description;

	public static TargetMoneyResponse from(TargetMoney targetMoney) {
		return new TargetMoneyResponse(targetMoney.getAmount(), targetMoney.getTargetMoneyType(), targetMoney.getDescription());
	}
}
