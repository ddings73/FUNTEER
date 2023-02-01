package com.yam.funteer.attach.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.attach.entity.TeamAttach;

import java.util.List;

public interface TeamAttachRepository extends JpaRepository<TeamAttach, Long> {
    List<TeamAttach> findAllByTeamId(Long id);
}
