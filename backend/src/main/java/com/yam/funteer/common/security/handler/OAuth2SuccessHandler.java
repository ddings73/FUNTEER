package com.yam.funteer.common.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yam.funteer.common.security.JwtProvider;
import com.yam.funteer.user.dto.response.TokenInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import java.io.IOException;

@Component @Slf4j
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String email = (String)oAuth2User.getAttributes().get("email");

        String targetURI = UriComponentsBuilder.fromUriString("https://funteer.site/login/kakao")
            .queryParam("email", email).toUriString();
        getRedirectStrategy().sendRedirect(request, response, targetURI);
    }
}
