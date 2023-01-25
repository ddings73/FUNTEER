package com.yam.funteer.user.service;

import com.yam.funteer.user.dto.request.BaseUserRequest;
import com.yam.funteer.user.dto.response.TeamProfileResponse;

public interface TeamService {
	// void signupTeam(CreateMemberRequest requestDto);
	void signoutTeam(BaseUserRequest baseUserRequest);
	TeamProfileResponse getTeamProfile(BaseUserRequest baseUserRequest);
}
