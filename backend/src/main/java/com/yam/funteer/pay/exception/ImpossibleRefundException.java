package com.yam.funteer.pay.exception;

public class ImpossibleRefundException extends Exception {

	String message;
	public ImpossibleRefundException(String message) {
		this.message = message;
	}
}
