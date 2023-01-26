package com.yam.funteer.user.service;

import com.yam.funteer.attach.repository.AttachRepository;
import com.yam.funteer.exception.EmailDuplicateException;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.repository.FundingRepository;
import com.yam.funteer.user.dto.request.CreateMemberRequest;
import com.yam.funteer.user.dto.request.FollowRequest;
import com.yam.funteer.user.dto.response.MemberProfileResponse;
import com.yam.funteer.user.dto.request.BaseUserRequest;
import com.yam.funteer.user.entity.Follow;
import com.yam.funteer.user.entity.Member;
import com.yam.funteer.user.entity.Team;
import com.yam.funteer.user.entity.Wish;
import com.yam.funteer.user.repository.FollowRepository;
import com.yam.funteer.user.repository.MemberRepository;
import com.yam.funteer.user.repository.TeamRepository;
import com.yam.funteer.user.repository.WishRepository;

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
    private final FundingRepository fundingRepository;
    private final WishRepository wishRepository;


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

        long followCnt = followRepository.countAllByMember(member);
        long wishCnt = wishRepository.countAllByMember(member);

        MemberProfileResponse memberProfileResponse = MemberProfileResponse.of(member);
        return memberProfileResponse;
    }

    @Override
    public void followTeam(FollowRequest followRequest) {
        Optional<Member> findMember = memberRepository.findById(followRequest.getMemberId());
        Optional<Team> findTeam = teamRepository.findById(followRequest.getTeamId());
        Member member = findMember.orElseThrow(UserNotFoundException::new);
        Team team = findTeam.orElseThrow(UserNotFoundException::new);

        Optional<Follow> findFollow = followRepository.findByMemberAndTeam(member, team);
        findFollow.ifPresentOrElse(follow -> follow.toggle(), ()->{
            Follow newFollow = Follow.of(member, team);
            followRepository.save(newFollow);
        });
    }

    @Override
    public void wishFunding(Long fundingId, Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        Optional<Funding> findFunding = fundingRepository.findById(fundingId);
        Member member = findMember.orElseThrow(UserNotFoundException::new);
        Funding funding = findFunding.orElseThrow(IllegalArgumentException::new);

        Optional<Wish> findWish = wishRepository.findByMemberAndFunding(member, funding);
        findWish.ifPresentOrElse(wish -> wish.toggle(), ()->{
            Wish newWish = Wish.of(member, funding);
            wishRepository.save(newWish);
        });
    }

}