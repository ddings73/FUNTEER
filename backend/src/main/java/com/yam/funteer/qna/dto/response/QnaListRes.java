package com.yam.funteer.qna.dto.response;

import com.yam.funteer.qna.entity.Qna;

import lombok.Getter;

@Getter
public class QnaListRes {
	private String title;
	private Long id;
	private boolean respond;

	public QnaListRes(Qna qna){
		this.id=qna.getQnaId();
		this.title=qna.getTitle();
		this.respond=qna.isRespond();
	}
}
