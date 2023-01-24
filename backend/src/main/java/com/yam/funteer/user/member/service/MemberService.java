package com.yam.funteer.user.member.service;

import com.yam.funteer.user.member.dto.CreateMemberRequest;
import com.yam.funteer.user.member.dto.MemberProfileResponse;
import com.yam.funteer.user.member.dto.SelectMemberRequest;

public interface MemberService {

    void signupMember(CreateMemberRequest requestDto);
    void signoutMember(SelectMemberRequest selectMemberRequest);
    MemberProfileResponse getMemberProfile(SelectMemberRequest selectMemberRequest);
}
