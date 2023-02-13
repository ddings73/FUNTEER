package com.yam.funteer.funding.exception;

public class NotFoundReportException extends RuntimeException{

	public NotFoundReportException() {
		super("보고서가 존재하지 않습니다.");
	}
}
