package com.yam.funteer.alarm.dto.response;

import java.time.LocalDateTime;

import com.yam.funteer.alarm.entity.Alarm;


public class AlarmRes {
	private Long id;
	private Long userId;
	private String title;
	private String content;
	private boolean checked;
	private LocalDateTime date;

	public AlarmRes(Alarm entity){
		this.id=entity.getId();
		this.userId=entity.getUser().getId();
		this.title=entity.getTitle();
		this.content=entity.getContent();
		this.checked=entity.isChecked();
		this.date=LocalDateTime.now();
	}
}