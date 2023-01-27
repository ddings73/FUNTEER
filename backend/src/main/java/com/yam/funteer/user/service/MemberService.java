package com.yam.funteer.user.service;

import com.yam.funteer.user.dto.request.CreateMemberRequest;
import com.yam.funteer.user.dto.response.MemberProfileResponse;
import com.yam.funteer.user.dto.request.BaseUserRequest;

public interface MemberService {

    void signupMember(CreateMemberRequest requestDto);
    void signoutMember(BaseUserRequest baseUserRequest);
    MemberProfileResponse getMemberProfile(BaseUserRequest baseUserRequest);

    void followTeam(Long teamId, Long memberId);
    void wishFunding(Long fundingId, Long memberId);
}
