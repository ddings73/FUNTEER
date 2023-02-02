package com.yam.funteer.badge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.badge.entity.Badge;

public interface BadgeRepository extends JpaRepository<Badge,Long> {
	List<Badge>findAllByIdBetween(Long id1,Long id2);
}
