package com.yam.funteer.post;

import com.yam.funteer.common.CommonCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum PostType implements CommonCode {

	D00("모금"), D01("모금 활성화"),D02("모금 비활성화"),
	D10("펀딩"), D11("펀딩 승인 대기"), D12("펀딩 승인 거부"), D13("펀딩 승인 완료"),
	D14("펀딩 진행중"), D15("펀딩 기간 연장 함"), D16("펀딩 완료"), D17("펀딩 실패"), D18("봉사 종료");

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
