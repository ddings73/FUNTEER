package com.yam.funteer.notice.dto.response;

import java.time.LocalDate;

import com.yam.funteer.notice.entity.Notice;
import com.yam.funteer.post.entity.Post;

import lombok.Getter;

@Getter
public class NoticeListRes {
	private String title;
	private Long id;
	private LocalDate localDate;

	public NoticeListRes(Notice notice){
		this.title=notice.getTitle();
		this.id=notice.getNoticeId();
		this.localDate=notice.getRegDate().toLocalDate();
	}
}