package com.yam.funteer.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.user.entity.Follow;

public interface FollowRepository extends JpaRepository<Follow, Long> {
	long countAllByTeamId(Long teamId);
}
