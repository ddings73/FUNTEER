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

        // 여기서 실제로 검증
        // UserDetailsService를 상속받는 bean을 찾아서 loadUserByUsername을 실행함
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenInfo tokenInfo = jwtProvider.generateToken(authentication); // 검증되면 jwt 만들어서 가져옴


        // 생성된 토큰을 DB에 저장함
        Long userId = Long.valueOf(authentication.getName());
        Token token = Token.builder()
                .id(userId)
                .refreshToken(tokenInfo.getRefreshToken())
                .build();
        tokenRepository.save(token);

        User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(UserNotFoundException::new);
        if(user.isResign()){
            throw new IllegalArgumentException("탈퇴한 회원입니다");
        }else if(user.getUserType().equals(UserType.TEAM_WAIT)){
            throw new IllegalArgumentException("가입 대기중인 회원입니다");
        }

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
            throw new JwtException("Refresh Token이 유효하지 않습니다.");
        }

        Authentication authentication = jwtProvider.getAuthentication(refreshToken);
        Long userId = Long.valueOf(authentication.getName());
        Token token = tokenRepository.findById(Long.valueOf(userId))
                .orElseThrow(()->new JwtException("로그아웃 된 사용자입니다."));

        if (!token.getRefreshToken().equals(refreshToken)) {
            throw new JwtException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        if(jwtProvider.validateToken(accessToken)){
            tokenRepository.deleteById(userId);
            throw new JwtException("Access Token이 만료되지 않았습니다.");
        }

        TokenInfo tokenInfo = jwtProvider.generateToken(authentication);
        token.update(tokenInfo.getRefreshToken());
        return tokenInfo;
    }
}
