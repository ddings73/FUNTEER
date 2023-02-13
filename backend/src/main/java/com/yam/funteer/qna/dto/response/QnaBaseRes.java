package com.yam.funteer.qna.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.yam.funteer.qna.entity.Qna;

import lombok.Getter;

@Getter
public class QnaBaseRes {
	private Long id;
	private Long userId;
	private String title;
	private String content;
	private LocalDateTime regDate;
	private List<Map.Entry<String,String>> files;

	private boolean respond;

	public QnaBaseRes(Qna entity,List<Map.Entry<String,String>> files) {
		this.id = entity.getQnaId();
		this.userId=entity.getUser().getId();
		this.title=entity.getTitle();
		this.content=entity.getContent();
		this.regDate=entity.getRegDate();
		this.files=files;
		this.respond=entity.isRespond();
	}
}
