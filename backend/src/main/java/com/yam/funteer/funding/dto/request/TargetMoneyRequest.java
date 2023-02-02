package com.yam.funteer.funding.dto.request;

import java.util.List;

import com.yam.funteer.common.code.TargetMoneyType;
import com.yam.funteer.funding.entity.TargetMoney;

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
	private List<TargetMoneyDetailRequest> descriptions;

}
