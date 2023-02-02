package com.yam.funteer.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.user.entity.UserBadge;

public interface UserBadgeRepository extends JpaRepository<UserBadge,Long> {
}
