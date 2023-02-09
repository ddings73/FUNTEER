package com.yam.funteer.faq.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.yam.funteer.post.entity.Post;

import lombok.Getter;

@Getter
public class FaqBaseRes {
	private String title;
	private Long id;
	private String content;
	private LocalDate localDate;

	public FaqBaseRes(Post entity){
		this.title=entity.getTitle();
		this.id=entity.getId();
		this.content=entity.getContent();
		this.localDate=entity.getRegDate().toLocalDate();
	}
}
