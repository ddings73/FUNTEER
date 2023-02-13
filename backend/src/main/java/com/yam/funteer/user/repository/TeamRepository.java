package com.yam.funteer.user.repository;

import com.yam.funteer.common.code.UserType;
import com.yam.funteer.user.entity.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByEmail(String email);

    Page<Team> findAllByNameContaining(String keyword, Pageable pageable);

    Page<Team> findAllByNameContainingAndUserType(String keyword, UserType userType, Pageable pageable);
}
