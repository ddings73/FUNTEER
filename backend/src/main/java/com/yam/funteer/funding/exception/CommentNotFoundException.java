package com.yam.funteer.funding.exception;

public class CommentNotFoundException extends RuntimeException {
	public CommentNotFoundException() {
		super("댓글이 존재하지 않습니다.");
	}

}