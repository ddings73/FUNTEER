package com.yam.funteer.alarm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.yam.funteer.user.entity.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PastAlarm {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long AlarmId;

	@ManyToOne
	@JoinColumn
	private User user;

	private String content;

	private String url;

	private Boolean isRead;

	public void setRead(){
		this.isRead=true;
	}

}
