package com.yam.funteer.qna.response;

import java.time.LocalDateTime;

import com.yam.funteer.qna.entity.Qna;

import lombok.Getter;

@Getter
public class QnaBaseRes {
	private Long id;
	private Long userId;
	private String title;
	private String content;
	private LocalDateTime regDate;

	public QnaBaseRes(Qna entity) {
		this.id = entity.getId();
		this.userId=entity.getUser().getId();
		this.title=entity.getTitle();
		this.content=entity.getContent();
		this.regDate=entity.getRegDate();
	}
}
