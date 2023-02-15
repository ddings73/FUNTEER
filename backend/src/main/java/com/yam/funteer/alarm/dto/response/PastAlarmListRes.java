package com.yam.funteer.alarm.dto.response;

import com.yam.funteer.alarm.entity.AlarmEntity;

import lombok.Getter;

@Getter
public class PastAlarmListRes {
	private String content;
	private Long alarmId;
	private String userEmail;
	private String url;

	public  PastAlarmListRes(AlarmEntity entity){
		this.content=entity.getContent();
		this.alarmId=entity.getId();
		this.userEmail=entity.getUserEmail();
		this.url=entity.getUrl();
	}
}

