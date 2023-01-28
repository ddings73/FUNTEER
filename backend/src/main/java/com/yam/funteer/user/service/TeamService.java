package com.yam.funteer.user.service;

import com.yam.funteer.user.dto.request.BaseUserRequest;
import com.yam.funteer.user.dto.request.CreateAccountRequest;
import com.yam.funteer.user.dto.response.TeamProfileResponse;

public interface TeamService {
	void createAccountWithOutProfile(CreateAccountRequest request);
	void setAccountSignOut(BaseUserRequest request);
	TeamProfileResponse getTeamProfile(BaseUserRequest baseUserRequest);
}
