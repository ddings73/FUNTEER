package com.yam.funteer.qna.dto.response;

import lombok.Getter;

@Getter
public class QnaListRes {
	private String title;
	private Long id;

	public QnaListRes(Long id,String title){
		this.id=id;
		this.title=title;
	}
}
