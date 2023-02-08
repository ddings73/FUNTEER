package com.yam.funteer.faq.dto.response;

import java.util.List;

import com.yam.funteer.faq.entity.Faq;
import com.yam.funteer.post.entity.Post;

import lombok.Getter;

@Getter
public class FaqBaseRes {
	private String title;
	private Long id;
	private String content;

	public FaqBaseRes(Faq entity){
		this.title=entity.getTitle();
		this.id=entity.getFaqId();
		this.content=entity.getContent();
	}
}
