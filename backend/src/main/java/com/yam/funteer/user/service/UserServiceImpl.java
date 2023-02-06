package com.yam.funteer.user.service;

import com.yam.funteer.exception.DuplicateInfoException;
import com.yam.funteer.user.repository.MemberRepository;
import com.yam.funteer.user.repository.TeamRepository;
import com.yam.funteer.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final MemberRepository memberRepository;


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
}
