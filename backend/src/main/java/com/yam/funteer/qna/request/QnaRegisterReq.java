package com.yam.funteer.qna.request;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QnaRegisterReq {
	private Long userId;
	private String title;
	private String content;
	private String password;

}
