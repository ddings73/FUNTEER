package com.yam.funteer.user.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.user.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
