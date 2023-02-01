package com.yam.funteer.badge.dto.request;

import org.springframework.web.multipart.MultipartFile;

import com.yam.funteer.badge.entity.Badge;
import com.yam.funteer.user.entity.User;
import com.yam.funteer.user.entity.UserBadge;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BadgeRegisterReq {
	private String description;
	private String name;
	private MultipartFile imgFile;

	public Badge toBadgeEntity(String urlPath){
		return Badge.builder()
			.name(name)
			.description(description)
			.badgeImgPath(urlPath)
			.build();
	}

	public UserBadge toUserBadgeEntity(Badge badge, User user){
		return UserBadge.builder()
			.badge(badge).user(user)
			.build();
	}

}
