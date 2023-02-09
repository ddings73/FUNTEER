package com.yam.funteer.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.common.code.UserType;
import com.yam.funteer.user.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String nickname);

    Page<Member> findAllByNicknameContainingAndUserTypeIsNot(String nickname, UserType userType, Pageable pageable);

	Page<Member> findAllByNicknameContainingAndUserType(String keyword, UserType userType, Pageable pageable);
}
