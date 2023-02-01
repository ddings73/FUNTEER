package com.yam.funteer.funding.dto.request;

import com.yam.funteer.common.code.TargetMoneyType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TargetMoneyRequest {

	private TargetMoneyType targetMoneyType;
	private int amount;
	private String description;
}
