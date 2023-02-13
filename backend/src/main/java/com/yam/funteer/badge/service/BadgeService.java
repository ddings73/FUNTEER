package com.yam.funteer.badge.service;

import com.yam.funteer.badge.dto.reponse.BadgeBaseRes;
import com.yam.funteer.badge.dto.request.BadgeRegisterReq;
import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.common.code.UserType;
import com.yam.funteer.user.entity.Team;
import com.yam.funteer.user.entity.User;

public interface BadgeService {
	void initBadges(User user);
	void postBadges(User user, PostGroup postGroup);
	void totalPayAmount(User user);
	void teamFundingBadges(Team team);

}
