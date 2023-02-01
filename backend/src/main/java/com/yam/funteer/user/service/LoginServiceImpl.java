package com.yam.funteer.user.service;

import com.yam.funteer.common.code.UserType;
import com.yam.funteer.common.security.SecurityUtil;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.user.dto.request.TokenRequest;
import com.yam.funteer.user.dto.response.TokenInfo;
import com.yam.funteer.user.entity.Token;
import com.yam.funteer.common.security.JwtProvider;
import com.yam.funteer.user.dto.request.LoginRequest;
import com.yam.funteer.user.dto.response.LoginResponse;
import com.yam.funteer.user.entity.User;
import com.yam.funteer.user.repository.TokenRepository;
import com.yam.funteer.user.repository.UserRepository;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service @Slf4j
@Transactional
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{
    private final JwtProvider jwtProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    @Override
    public LoginResponse processLogin(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        TokenInfo tokenInfo = jwtProvider.generateToken(authentication); // 검증되면 jwt 만들어서 가져옴
        Long userId = Long.valueOf(authentication.getName());
        User user = userFoundProcess(loginRequest, tokenInfo, userId);

        user.validate();

        return LoginResponse.of(user, tokenInfo);
    }

    @Override
    public LoginResponse processKakaoLogin(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        TokenInfo tokenInfo = jwtProvider.generateToken(authentication); // 검증되면 jwt 만들어서 가져옴
        Long userId = Long.valueOf(authentication.getName());
        User user = userFoundProcess(loginRequest, tokenInfo, userId);

        return LoginResponse.of(user, tokenInfo);
    }

    @Override
    public void processLogOut() {
        Long userId = SecurityUtil.getCurrentUserId();
        tokenRepository.deleteById(userId);
    }

    @Override
    public TokenInfo regenerateToken(TokenRequest tokenRequest) {
        String accessToken = tokenRequest.getAccessToken();
        String refreshToken = tokenRequest.getRefreshToken();

        if(!jwtProvider.validateToken(refreshToken)){
            throw new AccessDeniedException("Refresh Token이 유효하지 않습니다.");
        }

        Token token = tokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(()->new AccessDeniedException("로그아웃된 사용자 혹은 유효하지 않은 토큰입니다."));

        Long userId = token.getId();
        // access 토큰 만료 확인
        if(jwtProvider.validateToken(accessToken)){
            tokenRepository.deleteById(userId);
            throw new AccessDeniedException("Access Token이 만료되지 않았습니다.");
        }


        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        TokenInfo tokenInfo = jwtProvider.createToken(String.valueOf(userId), user.getUserType().getAuthority());
        token.update(tokenInfo.getRefreshToken());
        return tokenInfo;
    }


    private User userFoundProcess(LoginRequest loginRequest, TokenInfo tokenInfo, Long userId){
        Token token = Token.from(userId, tokenInfo.getRefreshToken());
        tokenRepository.save(token);

        return userRepository.findById(Long.valueOf(userId)).orElseThrow(UserNotFoundException::new);
    }
}
