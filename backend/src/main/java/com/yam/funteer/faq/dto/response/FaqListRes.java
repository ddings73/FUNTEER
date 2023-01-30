package com.yam.funteer.faq.dto.response;

import lombok.Getter;

@Getter
public class FaqListRes {
	private String title;
	private Long id;

	public FaqListRes(Long id,String title){
		this.title=title;
		this.id=id;
	}
}
