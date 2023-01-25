package com.yam.funteer.user.service;

import com.yam.funteer.attach.repository.AttachRepository;
import com.yam.funteer.exception.EmailDuplicateException;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.user.dto.request.CreateMemberRequest;
import com.yam.funteer.user.dto.response.MemberProfileResponse;
import com.yam.funteer.user.dto.request.BaseUserRequest;
import com.yam.funteer.user.entity.Member;
import com.yam.funteer.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final AttachRepository attachRepository;


    @Override
    public void signupMember(CreateMemberRequest requestDto) {
        Optional<Member> findMember = memberRepository.findByEmail(requestDto.getEmail());
        findMember.ifPresent(member -> {
            throw new EmailDuplicateException();
        });

        requestDto.encryptPassword(passwordEncoder);
        Member member = requestDto.toEntity();
        memberRepository.save(member);
    }

    @Override
    public void signoutMember(BaseUserRequest baseUserRequest) {
        Optional<Member> findMember = memberRepository.findById(baseUserRequest.getUserId());
        Member member = findMember.orElseThrow(UserNotFoundException::new);
        String password = baseUserRequest.getPassword().orElseThrow(IllegalArgumentException::new);

        if(!member.validatePassword(passwordEncoder, password))
            throw new IllegalArgumentException();
        member.signOut();
    }

    @Override
    public MemberProfileResponse getMemberProfile(BaseUserRequest baseUserRequest) {
        Long memberId = baseUserRequest.getUserId();
        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = findMember.orElseThrow(UserNotFoundException::new);

        // 찜하기 조회
        // 팔로잉 조회
        MemberProfileResponse memberProfileResponse = MemberProfileResponse.of(member);
        return memberProfileResponse;
    }

}