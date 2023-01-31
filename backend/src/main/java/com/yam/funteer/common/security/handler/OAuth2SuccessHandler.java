package com.yam.funteer.common.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yam.funteer.common.code.UserType;
import com.yam.funteer.common.security.JwtProvider;
import com.yam.funteer.user.dto.response.TokenInfo;
import com.yam.funteer.user.entity.Member;
import com.yam.funteer.user.entity.User;
import com.yam.funteer.user.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@Component @Slf4j
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;


    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String userId = (String)oAuth2User.getAttributes().get("userId");
        TokenInfo tokenInfo = jwtProvider.generateTokenForOAuth(userId);

        String jsonStr = objectMapper.writeValueAsString(tokenInfo);

        log.info(jsonStr);

        HttpSession session = request.getSession();
        session.setAttribute("token", jsonStr);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("https://i8e204.p.ssafy.io/");
    }
}
