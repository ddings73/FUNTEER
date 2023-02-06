package com.yam.funteer.funding.exception;

public class NotAuthenticatedTeamException extends Exception{

	public NotAuthenticatedTeamException() {};

	public NotAuthenticatedTeamException(String message) {
		super(message);
	}
}
