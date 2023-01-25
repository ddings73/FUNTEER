package com.yam.funteer.post;

import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
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
	));
	private final List<PostType> typeList;
}
