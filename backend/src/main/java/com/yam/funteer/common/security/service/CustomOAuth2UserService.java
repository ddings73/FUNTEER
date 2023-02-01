package com.yam.funteer.common.security.service;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import com.yam.funteer.common.code.UserType;
import com.yam.funteer.user.entity.Member;
import com.yam.funteer.user.repository.MemberRepository;

@Service @Slf4j
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();

        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest); // accessToken으로 카카오 서버에서 유저정보 받아오기

        ClientRegistration clientRegistration = userRequest.getClientRegistration();

        String provider = clientRegistration.getRegistrationId(); // 제공자
        String attributeKey = clientRegistration
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        log.info("provider => {}", provider); // kakao
        log.info("attributeKey => {}", attributeKey); // id

        OAuth2Attribute oAuth2Attribute = OAuth2Attribute.of(provider, attributeKey, oAuth2User.getAttributes());

        log.info("{}", oAuth2Attribute);

        Map<String, Object> memberAttribute = oAuth2Attribute.convertToMap();

        String email = (String) memberAttribute.get("email");
        String name = (String) memberAttribute.get("name");

        Member member = memberRepository.findByEmail(email).orElseGet(() -> {
            String kakaoPassword = passwordEncoder.encode("kakaoPassword");
            Member newKakaoMember = Member.toKakaoUser(email, name, kakaoPassword);
            return memberRepository.save(newKakaoMember);
        });

        memberAttribute.put("userId", String.valueOf(member.getId()));
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(UserType.KAKAO.getAuthority())),
                memberAttribute, "userId");
    }
}
