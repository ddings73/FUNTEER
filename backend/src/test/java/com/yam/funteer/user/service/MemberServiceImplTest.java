package com.yam.funteer.user.member.service;

import com.yam.funteer.user.UserType;
import com.yam.funteer.user.dto.CreateMemberRequest;
import com.yam.funteer.user.entity.Member;
import com.yam.funteer.user.repository.MemberRepository;
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


    @Test
    @DisplayName("MemberRequestDto to Entity 테스트")
    void requestToEntityTest(){
        CreateMemberRequest requestDto = CreateMemberRequest.builder()
                .email("kim@ssafy.com")
                .name("김싸피")
                .nickname("SSAFY")
                .password("qwer1234")
                .phone("010-1234-5678")
                .build();

        Member member = requestDto.toEntity();
        assertNotNull(member);
        assertEquals(UserType.NORMAL, member.getStatus());
        assertEquals(0L, member.getMoney());
    }

    @Test
    @DisplayName("이메일로 회원 찾기 테스트")
    void findByEmailTest(){
        CreateMemberRequest requestDto = CreateMemberRequest.builder()
                .email("kim@ssafy.com")
                .name("김싸피")
                .nickname("SSAFY")
                .password("qwer1234")
                .phone("010-1234-5678")
                .build();

        Member member = requestDto.toEntity();

        Optional<Member> byEmail = memberRepository.findByEmail(requestDto.getEmail());
        assertTrue(byEmail.isEmpty());

        memberRepository.save(member);
        assertNotNull(member.getId());
    }
}