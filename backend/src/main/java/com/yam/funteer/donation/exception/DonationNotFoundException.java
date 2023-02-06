package com.yam.funteer.donation.exception;

import lombok.Getter;

@Getter
public class DonationNotFoundException extends RuntimeException {
	public DonationNotFoundException() {
		super("해당 도네이션이 없습니다.");
	}
}
