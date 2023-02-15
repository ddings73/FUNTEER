package com.yam.funteer.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter @ToString
@RequiredArgsConstructor
public enum PostType implements TypeModel {
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
	NOTICE("공지사항"),
	FAQ("FAQ");

	private final String description;

	@Override
	public String getKey() {
		return name();
	}

	public static List<PostType> collectPostType(PostType postType){
		switch(postType){
			case FUNDING_ACCEPT: return List.of(FUNDING_ACCEPT);
			case FUNDING_IN_PROGRESS: return List.of(FUNDING_IN_PROGRESS, FUNDING_EXTEND);
			case FUNDING_COMPLETE: return List.of(FUNDING_COMPLETE, REPORT_WAIT, REPORT_ACCEPT);
			default: return List.of(FUNDING_FAIL);
		}
	}

}


