package com.yam.funteer.user.service;

import com.yam.funteer.attach.repository.AttachRepository;
import com.yam.funteer.exception.EmailDuplicateException;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.user.dto.request.CreateMemberRequest;
import com.yam.funteer.user.dto.request.FollowRequest;
import com.yam.funteer.user.dto.response.MemberProfileResponse;
import com.yam.funteer.user.dto.request.BaseUserRequest;
import com.yam.funteer.user.entity.Follow;
import com.yam.funteer.user.entity.Member;
import com.yam.funteer.user.entity.Team;
import com.yam.funteer.user.repository.FollowRepository;
import com.yam.funteer.user.repository.MemberRepository;
import com.yam.funteer.user.repository.TeamRepository;

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
    private final TeamRepository teamRepository;
    private final AttachRepository attachRepository;
    private final FollowRepository followRepository;


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

    @Override
    public void followTeam(FollowRequest followRequest) {
        Optional<Member> findMember = memberRepository.findById(followRequest.getMemberId());
        Optional<Team> findTeam = teamRepository.findById(followRequest.getTeamId());
        Member member = findMember.orElseThrow(UserNotFoundException::new);
        Team team = findTeam.orElseThrow(UserNotFoundException::new);

        Optional<Follow> findFollow = followRepository.findByMemberTeam(member, team);
        findFollow.ifPresentOrElse(follow -> follow.toggle(), ()->{
            Follow newFollow = Follow.of(member, team);
            followRepository.save(newFollow);
        });
    }

}