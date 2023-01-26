package com.yam.funteer.qna.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaTeamRegisterReq {
	private Long teamId;
	private String title;
	private String content;
	private String password;
}
