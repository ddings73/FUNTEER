package com.yam.funteer.user.service;

import com.yam.funteer.user.dto.CreateMemberRequest;
import com.yam.funteer.user.dto.MemberProfileResponse;
import com.yam.funteer.user.dto.SelectMemberRequest;

public interface MemberService {

    void signupMember(CreateMemberRequest requestDto);
    void signoutMember(SelectMemberRequest selectMemberRequest);
    MemberProfileResponse getMemberProfile(SelectMemberRequest selectMemberRequest);
}
