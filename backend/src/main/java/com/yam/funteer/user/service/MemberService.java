package com.yam.funteer.user.service;

import com.yam.funteer.user.dto.request.CreateAccountRequest;
import com.yam.funteer.user.dto.request.UpdateAccountRequest;
import com.yam.funteer.user.dto.request.UpdateProfileRequest;
import com.yam.funteer.user.dto.response.MemberAccountResponse;
import com.yam.funteer.user.dto.response.MemberProfileResponse;
import com.yam.funteer.user.dto.request.BaseUserRequest;

public interface MemberService {

    void createAccountWithOutProfile(CreateAccountRequest request);
    void setAccountSignOut(BaseUserRequest request);
    MemberProfileResponse getProfile(Long userId);
    void updateProfile(UpdateProfileRequest request);

    MemberAccountResponse getAccount(Long userId);
    void updateAccount(UpdateAccountRequest request);

    void followTeam(Long teamId, Long memberId);
    void wishFunding(Long fundingId, Long memberId);

}
