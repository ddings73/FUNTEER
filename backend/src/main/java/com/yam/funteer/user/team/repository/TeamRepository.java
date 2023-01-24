package com.yam.funteer.user.team.repository;

import com.yam.funteer.user.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByEmail(String email);
}
