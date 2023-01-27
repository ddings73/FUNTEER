package com.yam.funteer.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yam.funteer.common.code.UserType;
import com.yam.funteer.user.dto.request.LoginRequest;
import com.yam.funteer.user.dto.response.LoginResponse;
import com.yam.funteer.user.service.LoginService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LoginService loginService;

    @Test
    @DisplayName("로그인 정상적인 정보 테스트")
    void LoginTest01() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("kim@ssafy.com");
        loginRequest.setType(UserType.NORMAL);
        loginRequest.setPassword("Qwer1234@");

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUsername("ssafy");
        loginResponse.setUserId(1L);


        given(loginService.processLogin(loginRequest)).willReturn(loginResponse);


        String jsonStr = objectMapper.writeValueAsString(loginRequest);

        this.mockMvc.perform(get("/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonStr))
                .andDo(print())
                .andExpect(status().isOk());
    }
}