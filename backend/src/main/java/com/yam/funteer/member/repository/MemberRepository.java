package com.yam.funteer.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
