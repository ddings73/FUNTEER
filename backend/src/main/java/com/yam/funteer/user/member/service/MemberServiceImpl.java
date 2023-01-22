package com.yam.funteer.user.member.service;

import com.yam.funteer.common.repository.AttachRepository;
import com.yam.funteer.user.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final AttachRepository attachRepository;


}
