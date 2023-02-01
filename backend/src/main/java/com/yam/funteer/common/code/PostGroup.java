package com.yam.funteer.common.code;

import java.util.List;

import com.yam.funteer.common.code.PostType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter @ToString
@RequiredArgsConstructor
public enum PostGroup {
	FUNDING(List.of(
		PostType.FUNDING_WAIT,
		PostType.FUNDING_REJECT,
		PostType.FUNDING_ACCEPT,
		PostType.FUNDING_IN_PROGRESS,
		PostType.FUNDING_EXTEND,
		PostType.FUNDING_COMPLETE,
		PostType.FUNDING_FAIL
	)),
	DONATION(List.of(
		PostType.DONATION_ACTIVE,
		PostType.DONATION_CLOSE
	)),
	REPORT(List.of(
		PostType.REPORT_WAIT,
		PostType.REPORT_REJECT,
		PostType.REPORT_ACCEPT
	)),

	ETC(List.of(
		PostType.QNA,
		PostType.FAQ,
		PostType.NOTICE
	));

	private final List<PostType> typeList;
}
