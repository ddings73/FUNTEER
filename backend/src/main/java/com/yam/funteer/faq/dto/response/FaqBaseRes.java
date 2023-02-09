package com.yam.funteer.faq.dto.response;

import java.util.List;

import com.yam.funteer.post.entity.Post;

import lombok.Getter;

@Getter
public class FaqBaseRes {
	private String title;
	private Long id;
	private String content;

	public FaqBaseRes(Post entity){
		this.title=entity.getTitle();
		this.id=entity.getId();
		this.content=entity.getContent();
	}
}
