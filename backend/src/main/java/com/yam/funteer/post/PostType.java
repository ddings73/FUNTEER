package com.yam.funteer.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter @ToString
@RequiredArgsConstructor
public enum PostType{
	DONATION_ACTIVE("모금_진행"),
	DONATION_CLOSE("모금_종료"),

	FUNDING_WAIT("펀딩_승인_대기"),
	FUNDING_REJECT("펀딩_승인_거부"),
	FUNDING_ACCEPT("펀딩_승인_완료"),
	FUNDING_IN_PROGRESS("펀딩_진행중"),
	FUNDING_EXTEND("펀딩_연장"),
	FUNDING_COMPLETE("펀딩_완료"),
	FUNDING_FAIL("펀딩_실패"),

	REPORT_WAIT("보고서_승인대기"),
	REPORT_ACCEPT("보고서_승인완료"),
	REPORT_REJECT("보고서_승인거부"),

	QNA("QnA"),
	FAQ("FAQ");

	private final String description;
}
