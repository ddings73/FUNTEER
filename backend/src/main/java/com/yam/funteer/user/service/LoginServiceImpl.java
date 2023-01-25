package com.yam.funteer.user.service;

import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.user.dto.LoginRequest;
import com.yam.funteer.user.dto.LoginResponse;
import com.yam.funteer.user.entity.Member;
import com.yam.funteer.user.repository.MemberRepository;
import com.yam.funteer.user.entity.Team;
import com.yam.funteer.user.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    @Override
    public LoginResponse processLogin(LoginRequest loginRequest) {
        return loginRequest.isTeam() ? teamLogin(loginRequest) : memberLogin(loginRequest);
    }

    private LoginResponse memberLogin(LoginRequest loginRequest){
        Optional<Member> findMember = memberRepository.findByEmail(loginRequest.getEmail());
        Member member = findMember.orElseThrow(UserNotFoundException::new);

        validatePassword(loginRequest.getPassword(), member.getPassword());
        return LoginResponse.of(member, null, null);
    }
    private LoginResponse teamLogin(LoginRequest loginRequest){
        Optional<Team> fineTeam = teamRepository.findByEmail(loginRequest.getEmail());
        Team team = fineTeam.orElseThrow(UserNotFoundException::new);

        validatePassword(loginRequest.getPassword(), team.getPassword());
        return LoginResponse.of(team, null, null);
    }

    private void validatePassword(String p1, String p2){
        if(!passwordEncoder.matches(p1, p2)) throw new IllegalArgumentException();
    }
}
