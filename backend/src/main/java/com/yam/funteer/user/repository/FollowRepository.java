package com.yam.funteer.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.user.entity.Follow;
import com.yam.funteer.user.entity.Member;
import com.yam.funteer.user.entity.Team;

public interface FollowRepository extends JpaRepository<Follow, Long> {
	long countAllByTeam(Team team);
	long countAllByMemberAndChecked(Member member, Boolean checked);
	Optional<Follow> findByMemberAndTeam(Member member, Team team);
}
