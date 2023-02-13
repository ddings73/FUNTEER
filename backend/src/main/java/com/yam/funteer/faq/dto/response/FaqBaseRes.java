package com.yam.funteer.faq.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.yam.funteer.faq.entity.Faq;
import com.yam.funteer.post.entity.Post;

import lombok.Getter;

@Getter
public class FaqBaseRes {
	private String title;
	private Long id;
	private String content;
	private LocalDate localDate;
	private Long groupOrPerson;

	public FaqBaseRes(Faq entity){
		this.title=entity.getTitle();
		this.id=entity.getFaqId();
		this.content=entity.getContent();
		this.localDate=entity.getRegDate().toLocalDate();
		this.groupOrPerson=entity.getGroupOrPerson();
	}
}
