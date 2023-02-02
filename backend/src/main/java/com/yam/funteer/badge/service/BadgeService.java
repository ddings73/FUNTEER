package com.yam.funteer.badge.service;

import com.yam.funteer.badge.dto.reponse.BadgeBaseRes;
import com.yam.funteer.badge.dto.request.BadgeRegisterReq;

public interface BadgeService {

	BadgeBaseRes badgeRegister(BadgeRegisterReq badgeRegisterReq);
	BadgeBaseRes badgeCreate(BadgeRegisterReq badgeRegisterReq);
	BadgeBaseRes badgeUpdate(Long id,Long badgeId);

}
