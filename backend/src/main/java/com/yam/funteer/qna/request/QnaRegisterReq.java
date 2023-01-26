package com.yam.funteer.qna.request;

import com.yam.funteer.user.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaRegisterReq {
	private Long userId;
	private String title;
	private String content;
	private String password;
}
