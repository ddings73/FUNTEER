package com.yam.funteer.faq.dto.response;

import com.yam.funteer.post.entity.Post;

import lombok.Getter;

@Getter
public class FaqListRes {
	private String title;
	private Long id;

	public FaqListRes(Post post){
		this.title=post.getTitle();
		this.id=post.getId();
	}
}
