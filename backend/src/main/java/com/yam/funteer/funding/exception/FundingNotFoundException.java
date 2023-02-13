package com.yam.funteer.funding.exception;

public class FundingNotFoundException extends RuntimeException {
	public FundingNotFoundException() {
		super("펀딩을 찾을 수 없습니다.");
	}

}
