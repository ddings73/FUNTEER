package com.yam.funteer.user.service;

import com.yam.funteer.attach.repository.AttachRepository;
import com.yam.funteer.exception.EmailDuplicateException;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.post.TargetMoneyType;
import com.yam.funteer.user.dto.CreateMemberRequest;
import com.yam.funteer.user.dto.MemberProfileResponse;
import com.yam.funteer.user.dto.SelectMemberRequest;
import com.yam.funteer.user.entity.Member;
import com.yam.funteer.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service @Slf4j
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final PasswordEncoder passwordEncoder;
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
        requestDto.encryptPassword(passwordEncoder);
        Member member = requestDto.toEntity();
        memberRepository.save(member);
    }

    @Override
    public void signoutMember(SelectMemberRequest selectMemberRequest) {
        Optional<Member> findMember = memberRepository.findById(selectMemberRequest.getMemberId());
        Member member = findMember.orElseThrow(UserNotFoundException::new);
        String password = selectMemberRequest.getPassword().orElseThrow(IllegalArgumentException::new);

        validatePassword(password, member.getPassword());
        // member.signOut();
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

    private void validatePassword(String p1, String p2) {
        if(!passwordEncoder.matches(p1, p2)) throw new IllegalArgumentException();
    }
}