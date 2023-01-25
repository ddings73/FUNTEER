package com.yam.funteer.post;

import com.yam.funteer.common.CommonCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum TargetMoneyType implements CommonCode {
	C01("1단계"), C02("2단계"), C03("3단계");
	private String description;

	@Override
	public String getCode() {
		return null;
	}

	@Override
	public String getDescription() {
		return null;
	}
}
