package com.yam.funteer.notice.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.yam.funteer.donation.entity.Donation;
import com.yam.funteer.post.entity.Post;

import lombok.Getter;

@Getter
public class NoticeBaseRes {
	private Long id;
	private String title;
	private String content;
	private List<String> files;
	private LocalDate localDate;

	public NoticeBaseRes(Post entity,List<String>files){
		this.id=entity.getId();
		this.title=entity.getTitle();
		this.content=entity.getContent();
		this.files=files;
		this.localDate=entity.getRegDate().toLocalDate();
	}
}
