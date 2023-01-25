package com.yam.funteer.user.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yam.funteer.user.controller.MemberController;
import com.yam.funteer.user.dto.CreateMemberRequest;
import com.yam.funteer.user.service.MemberService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    MemberService memberService;

    @Test
    @DisplayName("유저_회원가입_정상입력")
    void signupSuccess() throws Exception {
        CreateMemberRequest requestDto = CreateMemberRequest.builder()
                .email("kim@ssafy.com")
                .name("김싸피")
                .nickname("SSAFY")
                .password("Q1w2e3r4@@")
                .phone("010-1234-5678")
                .build();

        String jsonStr = objectMapper.writeValueAsString(requestDto);

        this.mockMvc.perform(post("/member")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonStr))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("유저_회원가입_비정상입력")
    void signupFail() throws Exception{
        CreateMemberRequest requestDto = CreateMemberRequest.builder()
                .email("kimssafy.com")
                .name("김싸피")
                .nickname("SSAFY")
                .password(null)
                .phone("010-1234-5678")
                .build();

        String jsonStr = objectMapper.writeValueAsString(requestDto);
        this.mockMvc.perform(post("/member")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonStr))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}