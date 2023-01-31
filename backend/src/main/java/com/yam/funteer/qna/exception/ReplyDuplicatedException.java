package com.yam.funteer.qna.exception;

import lombok.Getter;

@Getter
public class ReplyDuplicatedException extends RuntimeException{
	public ReplyDuplicatedException(){
		super("이미 답변이 달렸습니다.");
	}

}
