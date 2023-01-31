package com.yam.funteer.qna.exception;

import lombok.Getter;

@Getter
public class ReplyNotFoundException extends RuntimeException{
	public ReplyNotFoundException(){
		super("찾을 수 없는 게시글입니다.");
	}

}
