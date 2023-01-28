package com.yam.funteer.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.yam.funteer.user.dto.request.CreateAccountRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.user.dto.request.BaseUserRequest;
import com.yam.funteer.user.dto.response.TeamProfileResponse;
import com.yam.funteer.user.entity.Team;
import com.yam.funteer.user.repository.FollowRepository;
import com.yam.funteer.user.repository.TeamRepository;

@Service @Slf4j
@Transactional
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService{

	// private final FundingRepository fundingRepository;
	private final FollowRepository followRepository;
	private final TeamRepository teamRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void createAccountWithOutProfile(CreateAccountRequest request) {
		Optional<Team> findTeam = teamRepository.findByEmail(request.getEmail());

	}

	@Override
	public void setAccountSignOut(BaseUserRequest baseUserRequest) {
		Optional<Team> findTeam = teamRepository.findById(baseUserRequest.getUserId());
		Team team = findTeam.orElseThrow(UserNotFoundException::new);
		String password = baseUserRequest.getPassword().orElseThrow(IllegalArgumentException::new);

		team.validatePassword(passwordEncoder, password);
		team.signOut();
	}


	@Override
	public TeamProfileResponse getTeamProfile(BaseUserRequest baseUserRequest) {
		Optional<Team> findTeam = teamRepository.findById(baseUserRequest.getUserId());
		Team team = findTeam.orElseThrow(UserNotFoundException::new);
		List<Funding> fundingList = new ArrayList<>(); // fundingRepository.findAllByTeamId(team.getId());
		long followerCnt = followRepository.countAllByTeam(team);

		return TeamProfileResponse.of(team, fundingList, followerCnt);
	}


}
