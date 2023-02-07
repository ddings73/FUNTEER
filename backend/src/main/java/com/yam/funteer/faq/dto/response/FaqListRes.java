package com.yam.funteer.faq.dto.response;

import java.time.LocalDate;

import com.yam.funteer.post.entity.Post;

import lombok.Getter;

@Getter
public class FaqListRes {
	private String title;
	private Long id;
	private LocalDate localDate;

	public FaqListRes(Post post){
		this.title=post.getTitle();
		this.id=post.getId();
		this.localDate=post.getRegDate().toLocalDate();
	}
}
