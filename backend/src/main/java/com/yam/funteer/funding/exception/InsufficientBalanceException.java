package com.yam.funteer.funding.exception;

public class InsufficientBalanceException extends Exception{

	public InsufficientBalanceException() {};

	public InsufficientBalanceException(String message) {
		super(message);
	}
}
