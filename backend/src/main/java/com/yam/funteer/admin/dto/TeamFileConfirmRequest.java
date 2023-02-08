package com.yam.funteer.admin.dto;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class TeamFileConfirmRequest {
	private Long fileId;
	private Optional<String> rejectComment;

}
