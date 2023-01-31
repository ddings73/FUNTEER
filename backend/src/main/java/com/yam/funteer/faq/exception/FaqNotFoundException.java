package com.yam.funteer.faq.exception;

import lombok.Getter;

@Getter
public class FaqNotFoundException extends RuntimeException{
	public FaqNotFoundException(){
		super("찾을 수 없는 게시글입니다.");
	}

}
