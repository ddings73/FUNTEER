package com.yam.funteer.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.badge.entity.Badge;
import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.post.entity.Post;
import com.yam.funteer.user.entity.User;
import com.yam.funteer.user.entity.UserBadge;

public interface UserBadgeRepository extends JpaRepository<UserBadge,Long> {
	List<UserBadge>findAllByBadgePostGroup(PostGroup postGroup);
	UserBadge findByUserAndBadge(User user, Badge badge);
}
