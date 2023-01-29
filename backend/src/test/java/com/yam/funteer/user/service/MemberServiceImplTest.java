package com.yam.funteer.user.service;

import com.yam.funteer.common.code.UserType;
import com.yam.funteer.user.dto.request.CreateAccountRequest;
import com.yam.funteer.user.entity.Follow;
import com.yam.funteer.user.entity.Member;
import com.yam.funteer.user.entity.Team;
import com.yam.funteer.user.repository.FollowRepository;
import com.yam.funteer.user.repository.MemberRepository;
import com.yam.funteer.user.repository.TeamRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberServiceImplTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private FollowRepository followRepository;


    @Test
    @DisplayName("MemberRequestDto to Entity 테스트")
    void requestToEntityTest(){
        CreateAccountRequest requestDto = CreateAccountRequest.builder()
                .email("kim@ssafy.com")
                .name("김싸피")
                .nickname("SSAFY")
                .password("qwer1234")
                .phone("010-1234-5678")
                .build();

        Member member = requestDto.toMember();
        assertNotNull(member);
        assertEquals(UserType.NORMAL, member.getUserType());
        assertEquals(0L, member.getMoney());
    }

    @Test
    @DisplayName("이메일로 회원 찾기 테스트")
    void findByEmailTest(){
        CreateAccountRequest requestDto = CreateAccountRequest.builder()
                .email("kim@ssafy.com")
                .name("김싸피")
                .nickname("SSAFY")
                .password("qwer1234")
                .phone("010-1234-5678")
                .build();

        Member member = requestDto.toMember();

        Optional<Member> byEmail = memberRepository.findByEmail(requestDto.getEmail());
        assertTrue(byEmail.isEmpty());

        memberRepository.save(member);
        assertNotNull(member.getId());
    }
    
    @Test
    @DisplayName("팔로우 테스트")
    void follow(){
        Member member = Member.builder()
            .email("kim@ssafy.com")
            .name("김싸피")
            .nickname("SSAFY")
            .password("qwer1234")
            .phone("010-1234-5678")
            .userType(UserType.NORMAL)
            .build();

        Team team = Team.builder()
            .email("team@ssafy.com")
            .name("그룹")
            .password("qwer1234")
            .phone("010-1234-5678")
            .userType(UserType.TEAM)
            .build();

        memberRepository.save(member);
        teamRepository.save(team);

        assertEquals(Optional.empty(), followRepository.findByMemberAndTeam(member, team));


        Follow newFollow = Follow.of(member, team);
        followRepository.save(newFollow);
        Optional<Follow> findFollow = followRepository.findByMemberAndTeam(member, team);
        assertNotEquals(Optional.empty(),findFollow);

        Follow follow = findFollow.get();
        assertEquals(newFollow, follow);

        assertTrue(follow.isFollow());

        follow.toggle();
        assertFalse(follow.isFollow());
    }
}