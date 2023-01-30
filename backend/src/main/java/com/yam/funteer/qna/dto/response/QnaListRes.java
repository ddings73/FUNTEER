package com.yam.funteer.qna.dto.response;

import com.yam.funteer.qna.entity.Qna;

import lombok.Getter;

@Getter
public class QnaListRes {
	private String title;
	private Long id;

	public QnaListRes(Qna qna){
		this.id=qna.getId();
		this.title=qna.getTitle();
	}
}
