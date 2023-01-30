package com.yam.funteer.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.user.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String nickname);
}
