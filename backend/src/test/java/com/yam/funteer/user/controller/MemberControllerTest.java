package com.yam.funteer.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yam.funteer.common.security.JwtProvider;
import com.yam.funteer.user.dto.request.CreateAccountRequest;
import com.yam.funteer.user.service.MemberService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
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

    @MockBean
    JwtProvider jwtProvider;

    @Test @WithMockUser
    @DisplayName("유저_회원가입_정상입력")
    void signupSuccess() throws Exception {
//        CreateAccountRequest requestDto = CreateAccountRequest.builder()
//                .email("kim@ssafy.com")
//                .name("김싸피")
//                .nickname("SSAFY")
//                .password("Q1w2e3r4@@")
//                .phone("010-1234-5678")
//                .build();
//
//        String jsonStr = objectMapper.writeValueAsString(requestDto);
//
//        this.mockMvc.perform(post("/member")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(jsonStr)
//                    .with(csrf()))
//                .andDo(print())
//                .andExpect(status().isOk());

    }

    @Test @WithMockUser
    @DisplayName("유저_회원가입_비정상입력")
    void signupFail() throws Exception{
//        CreateAccountRequest requestDto = CreateAccountRequest.builder()
//                .email("kimssafy.com")
//                .name("김싸피")
//                .nickname("SSAFY")
//                .password(null)
//                .phone("010-1234-5678")
//                .build();
//
//        String jsonStr = objectMapper.writeValueAsString(requestDto);
//        this.mockMvc.perform(post("/member")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(jsonStr)
//                    .with(csrf()))
//                .andDo(print())
//                .andExpect(status().isBadRequest());
    }
}