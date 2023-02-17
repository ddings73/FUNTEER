package com.yam.funteer.user.service;

import java.util.Map;
import java.util.Optional;

import com.yam.funteer.exception.DuplicateInfoException;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.user.dto.request.ForgetEmailRequest;
import com.yam.funteer.user.dto.request.PasswordUpdateRequest;
import com.yam.funteer.user.entity.User;
import com.yam.funteer.user.repository.MemberRepository;
import com.yam.funteer.user.repository.TeamRepository;
import com.yam.funteer.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void confirmEmail(String email) {
        userRepository.findByEmail(email).ifPresent(user->{
            throw new DuplicateInfoException("중복되는 이메일입니다.");  
        });
    }

    @Override
    public void confirmName(String name) {
        userRepository.findByName(name).ifPresent(user->{
            throw new DuplicateInfoException("중복되는 단체명입니다.");
        });
    }

    @Override
    public void confirmNickname(String nickname) {
        memberRepository.findByNickname(nickname).ifPresent(member->{
            throw new DuplicateInfoException("중복되는 닉네임입니다.");
        });
    }

    @Override
    public void confirmPhone(String phone) {
        userRepository.findByPhone(phone).ifPresent(member->{
            throw new DuplicateInfoException("중복되는 전화번호입니다.");
        });
    }

    @Override
    public Map<String, String> findEmail(ForgetEmailRequest request) {
        String name = request.getName();
        String phone = request.getPhone();

        User user = userRepository.findByNameAndPhone(name, phone).orElseThrow(UserNotFoundException::new);
        return Map.of("email", user.getEmail());
    }

    @Override
    public void updatePassword(PasswordUpdateRequest request) {
        String email = request.getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);

        String password = request.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.changePassword(encodedPassword);
    }
}
