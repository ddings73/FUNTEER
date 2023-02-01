package com.yam.funteer.badge.dto.reponse;

import com.yam.funteer.badge.entity.Badge;

import lombok.Getter;

@Getter
public class BadgeBaseRes {
	private Long badgeId;
	private String description;
	private String name;
	private String imgPath;

	public BadgeBaseRes(Badge entity){
		this.badgeId=entity.getId();
		this.description=entity.getDescription();
		this.name=entity.getName();
		this.imgPath=entity.getBadgeImgPath();
	}
}
