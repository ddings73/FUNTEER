package com.yam.funteer.badge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.badge.entity.Badge;

public interface BadgeRepository extends JpaRepository<Badge,Long> {

}
