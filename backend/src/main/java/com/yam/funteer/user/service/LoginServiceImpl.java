package com.yam.funteer.user.service;

import com.yam.funteer.common.security.SecurityUser;
import com.yam.funteer.common.security.Token;
import com.yam.funteer.common.security.JwtProvider;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.user.dto.request.LoginRequest;
import com.yam.funteer.user.dto.response.LoginResponse;
import com.yam.funteer.user.entity.User;
import com.yam.funteer.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;

    @Override
    public LoginResponse processLogin(LoginRequest loginRequest) {
        
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

        // 여기서 실제로 검증
        // UserDetailsService를 상속받는 bean을 찾아서 loadUserByUsername을 실행함
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken); 
        
        Token token = jwtProvider.generateToken(authentication); // 검증되면 jwt 만들어서 가져옴
        
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        
        return LoginResponse.of(securityUser.getUser(), token);
    }
}
