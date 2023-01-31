package com.yam.funteer.notice.exception;

import lombok.Getter;

@Getter
public class NoticeNotFoundException extends RuntimeException{
	public NoticeNotFoundException(){
		super("찾을 수 없는 게시글입니다.");
	}

}
