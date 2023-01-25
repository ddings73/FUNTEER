package com.yam.funteer.qna.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaMemberRegisterReq {
	private Long memberId;
	private String title;
	private String content;
	private String password;
}
