package com.yam.funteer.user.service;

import com.yam.funteer.user.dto.request.*;
import com.yam.funteer.user.dto.request.member.*;
import com.yam.funteer.user.dto.response.member.MemberAccountResponse;
import com.yam.funteer.user.dto.response.member.MemberProfileResponse;

public interface MemberService {

    void createAccountWithOutProfile(CreateMemberRequest request);
    void setAccountSignOut(BaseUserRequest baseUserRequest);
    MemberProfileResponse getProfile(Long userId);
    void updateProfile(UpdateMemberProfileRequest request);

    MemberAccountResponse getAccountInfo(Long userId);
    void updateAccount(BaseUserRequest request);

    void followTeam(FollowRequest followRequest);
    void wishFunding(WishRequest wishRequest);

    void chargeMileage(ChargeRequest chargeRequest);
}
