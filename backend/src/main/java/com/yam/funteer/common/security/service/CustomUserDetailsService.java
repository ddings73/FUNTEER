package com.yam.funteer.common.security.service;

import com.yam.funteer.common.security.SecurityUser;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.yam.funteer.user.entity.User user = userRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);
        return new SecurityUser(user);
    }
}
