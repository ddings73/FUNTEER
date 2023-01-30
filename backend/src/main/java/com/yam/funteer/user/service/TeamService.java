package com.yam.funteer.user.service;

import com.yam.funteer.user.dto.request.BaseUserRequest;
import com.yam.funteer.user.dto.request.team.CreateTeamRequest;
import com.yam.funteer.user.dto.request.team.UpdateTeamProfileRequest;
import com.yam.funteer.user.dto.response.team.TeamAccountResponse;
import com.yam.funteer.user.dto.response.team.TeamProfileResponse;

public interface TeamService {
	void createAccountWithOutProfile(CreateTeamRequest request);
	void setAccountSignOut(BaseUserRequest request);
	TeamProfileResponse getTeamProfile(Long userId);
    void updateProfile(UpdateTeamProfileRequest request);

	TeamAccountResponse getTeamAccount(Long userId);
}
