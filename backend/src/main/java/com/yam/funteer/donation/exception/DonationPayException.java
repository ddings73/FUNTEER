package com.yam.funteer.donation.exception;

import lombok.Getter;

@Getter
public class DonationPayException extends RuntimeException {
	public DonationPayException() {
		super("결제할 금액이 충전 금액보다 많습니다.");
	}
}
