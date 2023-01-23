package com.yam.funteer.user.member.service;

import com.yam.funteer.common.repository.AttachRepository;
import com.yam.funteer.exception.EmailDuplicateException;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.user.member.dto.CreateMemberRequest;
import com.yam.funteer.user.member.dto.MemberProfileResponse;
import com.yam.funteer.user.member.dto.SelectMemberRequest;
import com.yam.funteer.user.member.entity.Member;
import com.yam.funteer.user.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service @Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final AttachRepository attachRepository;


    @Override
    public void signupMember(CreateMemberRequest requestDto) {
        log.info("중복회원 검증 시작 => ");
        Optional<Member> findMember = memberRepository.findByEmail(requestDto.getEmail());
        if(findMember.isPresent()){
            throw new EmailDuplicateException();
        }

        log.info("중복회원 검증 종료 => ");
        requestDto.encryptPassword(); // security 미구현
        Member member = requestDto.toEntity();
        memberRepository.save(member);
    }

    @Override
    public void signoutUMember(SelectMemberRequest selectMemberRequest) {
        Optional<Member> findMember = memberRepository.findById(selectMemberRequest.getMemberId());
        Member member = findMember.orElseThrow(UserNotFoundException::new);
        String password = selectMemberRequest.getPassword().orElseThrow(IllegalArgumentException::new);

        // 암호화 로직 필요
        String encrptedPass = password;
        if(encrptedPass.equals(member.getPassword())){
            member.setSignOut();
        }

    }

    @Override
    public MemberProfileResponse getMemberProfile(SelectMemberRequest selectMemberRequest) {
        Long memberId = selectMemberRequest.getMemberId();
        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = findMember.orElseThrow(UserNotFoundException::new);

        // 짐하기 조회
        // 팔로잉 조회
        MemberProfileResponse memberProfileResponse = MemberProfileResponse.of(member);
        return memberProfileResponse;
    }
}
