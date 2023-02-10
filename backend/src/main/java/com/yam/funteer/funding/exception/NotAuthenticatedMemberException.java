package com.yam.funteer.funding.exception;

public class NotAuthenticatedMemberException extends Throwable {

	public NotAuthenticatedMemberException() {};

	public NotAuthenticatedMemberException(String message) {
		super("댓글을 삭제할 권한이 없습니다.");
	}
}
