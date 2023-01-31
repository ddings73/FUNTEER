package com.yam.funteer.qna.exception;

import lombok.Getter;

@Getter
public class QnaNotFoundException extends RuntimeException{
	public QnaNotFoundException(){
		super("찾을 수 없는 게시글입니다.");
	}

}
