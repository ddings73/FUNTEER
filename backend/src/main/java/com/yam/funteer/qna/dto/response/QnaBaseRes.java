package com.yam.funteer.qna.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.yam.funteer.qna.entity.Qna;

import lombok.Getter;

@Getter
public class QnaBaseRes {
	private Long id;
	private Long userId;
	private String title;
	private String content;
	private LocalDateTime regDate;
	private List<String>files;

	public QnaBaseRes(Qna entity,List<String>files) {
		this.id = entity.getId();
		this.userId=entity.getUser().getId();
		this.title=entity.getTitle();
		this.content=entity.getContent();
		this.regDate=entity.getRegDate();
		this.files=files;
	}
}
