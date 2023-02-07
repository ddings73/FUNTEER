package com.yam.funteer.attach.repository;

import com.yam.funteer.user.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.attach.entity.TeamAttach;

import java.util.List;

public interface TeamAttachRepository extends JpaRepository<TeamAttach, Long> {
    List<TeamAttach> findAllByTeam(Team team);
}
