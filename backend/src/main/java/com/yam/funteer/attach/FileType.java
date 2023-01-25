package com.yam.funteer.attach;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FileType {
	VMS("VMS 인증파일"),
	PERFORM("실적파일"),
	REPORT("보고서파일"),
	RECEIPT("영수증파일"),
	OTHER("기타파일");

	private final String description;
}
