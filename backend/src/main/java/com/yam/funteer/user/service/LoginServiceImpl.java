package com.yam.funteer.user.service;

import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.user.dto.LoginRequest;
import com.yam.funteer.user.dto.LoginResponse;
import com.yam.funteer.user.member.entity.Member;
import com.yam.funteer.user.member.repository.MemberRepository;
import com.yam.funteer.user.team.entity.Team;
import com.yam.funteer.user.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    @Override
    public LoginResponse processLogin(LoginRequest loginRequest) {
        return loginRequest.isTeam() ? teamLogin(loginRequest) : memberLogin(loginRequest);
    }

    private LoginResponse memberLogin(LoginRequest loginRequest){
        Optional<Member> findMember = memberRepository.findByEmail(loginRequest.getEmail());
        Member member = findMember.orElseThrow(UserNotFoundException::new);
        validatePassword(member.getPassword(), loginRequest.encryptedPassword());
        return LoginResponse.of(member, null, null);
    }
    private LoginResponse teamLogin(LoginRequest loginRequest){
        Optional<Team> fineTeam = teamRepository.findByEmail(loginRequest.getEmail());
        Team team = fineTeam.orElseThrow(UserNotFoundException::new);
        validatePassword(team.getPassword(), loginRequest.encryptedPassword());
        return LoginResponse.of(team, null, null);
    }

    private void validatePassword(String p1, String p2){
        if(!p1.equals(p2)) throw new IllegalArgumentException();
    }
}
