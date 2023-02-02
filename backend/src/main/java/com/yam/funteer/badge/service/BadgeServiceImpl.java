package com.yam.funteer.badge.service;

import org.springframework.stereotype.Service;

import com.yam.funteer.alarm.service.AlarmService;
import com.yam.funteer.badge.dto.reponse.BadgeBaseRes;
import com.yam.funteer.badge.dto.request.BadgeRegisterReq;
import com.yam.funteer.badge.entity.Badge;
import com.yam.funteer.badge.repository.BadgeRepository;
import com.yam.funteer.common.security.SecurityUtil;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.post.entity.Post;
import com.yam.funteer.user.entity.User;
import com.yam.funteer.user.entity.UserBadge;
import com.yam.funteer.user.repository.UserBadgeRepository;
import com.yam.funteer.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BadgeServiceImpl implements BadgeService {

	private final BadgeRepository badgeRepository;
	private final UserRepository userRepository;
	private final UserBadgeRepository userBadgeRepository;
	private final AlarmService alarmService;

	@Override
	public BadgeBaseRes badgeRegister(BadgeRegisterReq badgeRegisterReq) {
		return null;
	}

	@Override
	public BadgeBaseRes badgeCreate(BadgeRegisterReq badgeRegisterReq) {
		Badge badge=badgeRepository.findById(Long.valueOf(1)).orElseThrow(()->new IllegalArgumentException("뱃지가 존재하지 않습니다."));
		User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());
		UserBadge userBadge=badgeRegisterReq.toUserBadgeEntity(badge,user);
		userBadgeRepository.save(userBadge);
		return new BadgeBaseRes(badge);
	}

	@Override
	public BadgeBaseRes badgeUpdate(Long id,Long badgeId) {
		UserBadge userBadge=userBadgeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("사용자의 뱃지가 없습니다."));
		User user=userRepository.findById(userBadge.getUser().getId()).orElseThrow(()->new UserNotFoundException());
		Badge badge=badgeRepository.findById(badgeId).orElseThrow(()->new IllegalArgumentException("잘못된 뱃지입니다."));
		userBadge.upgrade(id,user,badge);
		alarmService.send(user.getEmail(), "뱃지를 획득했습니다."+badge.getName(),null);
		return new BadgeBaseRes(badge);
	}
}
