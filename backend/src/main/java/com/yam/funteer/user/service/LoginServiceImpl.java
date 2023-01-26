package com.yam.funteer.user.service;

import com.yam.funteer.common.security.Token;
import com.yam.funteer.common.security.JwtProvider;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.user.dto.request.LoginRequest;
import com.yam.funteer.user.dto.response.LoginResponse;
import com.yam.funteer.user.entity.User;
import com.yam.funteer.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

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
    private final UserRepository userRepository;

    @Override
    public LoginResponse processLogin(LoginRequest loginRequest) {
        Optional<User> findUser = userRepository.findByEmail(loginRequest.getEmail());
        User user = findUser.orElseThrow(UserNotFoundException::new);

        validatePassword(loginRequest.getPassword(), user.getPassword());

        String role = user.getUserType().getAuthority();
        Token token = jwtProvider.generateToken(user.getEmail(), role);
        return LoginResponse.of(user, token);
    }

    private void validatePassword(String p1, String p2){
        if(!passwordEncoder.matches(p1, p2)) throw new IllegalArgumentException();
    }
}
