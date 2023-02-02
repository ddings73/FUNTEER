package com.yam.funteer.badge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yam.funteer.alarm.service.AlarmService;
import com.yam.funteer.badge.dto.reponse.BadgeBaseRes;
import com.yam.funteer.badge.dto.request.BadgeRegisterReq;
import com.yam.funteer.badge.entity.Badge;
import com.yam.funteer.badge.repository.BadgeRepository;
import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.common.code.UserType;
import com.yam.funteer.common.security.SecurityUtil;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.repository.FundingRepository;
import com.yam.funteer.pay.entity.Payment;
import com.yam.funteer.pay.repository.PaymentRepository;
import com.yam.funteer.post.entity.Post;
import com.yam.funteer.user.entity.Team;
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
	private final FundingRepository fundingRepository;
	private final UserBadgeRepository userBadgeRepository;
	private final PaymentRepository paymentRepository;

	@Override
	public void initBadges(UserType userType) {
		if(userType.equals(UserType.TEAM_WAIT)){
			User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(UserNotFoundException::new);
			List<Badge> badgeList=badgeRepository.findAllByIdBetween(Long.valueOf(9),Long.valueOf(17));
			for(Badge badge:badgeList){
				UserBadge userBadge=UserBadge.builder()
					.user(user)
					.badge(badge)
					.achieve(false).build();
				userBadgeRepository.save(userBadge);
			}
		}else if(!userType.equals(UserType.ADMIN)){
			User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(UserNotFoundException::new);
			List<Badge> badgeList=badgeRepository.findAllByIdBetween(Long.valueOf(1),Long.valueOf(14));
			for(Badge badge:badgeList){
				UserBadge userBadge=UserBadge.builder()
					.user(user)
					.badge(badge)
					.achieve(false).build();
				userBadgeRepository.save(userBadge);

			}
		}
	}

	@Override
	public void postBadges(User user,PostGroup postGroup) {
		List<Payment> paymentList=paymentRepository.findAllByUserAndPostPostGroup(user,postGroup);
		List<Long> badgeIds=new ArrayList<>();

		amountBadge(postGroup, paymentList, badgeIds);
		countBadge(postGroup, paymentList, badgeIds);

		cal(user,badgeIds);
	}

	private void countBadge(PostGroup postGroup, List<Payment> paymentList, List<Long> badgeIds) {
		Integer count=paymentList.size();
		if ( count>=5){
			if(postGroup.equals(PostGroup.DONATION)){
				badgeIds.add(Long.valueOf(12));
			}else if(postGroup.equals(PostGroup.FUNDING)){
				badgeIds.add(Long.valueOf(5));
			}
		}
		if( count>=10) {
			if(postGroup.equals(PostGroup.DONATION)){
				badgeIds.add(Long.valueOf(13));
			}else if(postGroup.equals(PostGroup.FUNDING)){
				badgeIds.add(Long.valueOf(6));
			}

		}
		if(count>=30){
			if(postGroup.equals(PostGroup.DONATION)){
				badgeIds.add(Long.valueOf(14));
			}else if(postGroup.equals(PostGroup.FUNDING)){
				badgeIds.add(Long.valueOf(7));
			}
		}
	}

	private void amountBadge(PostGroup postGroup, List<Payment> paymentList, List<Long> badgeIds) {
		Long amount=paymentList.stream().mapToLong(payment -> payment.getAmount()).sum();
		if ( amount>=50000){
			if(postGroup.equals(PostGroup.DONATION)){
				badgeIds.add(Long.valueOf(9));
			}else if(postGroup.equals(PostGroup.FUNDING)){
				badgeIds.add(Long.valueOf(2));
			}
		}
		if( amount>=100000) {
			if(postGroup.equals(PostGroup.DONATION)){
				badgeIds.add(Long.valueOf(10));
			}else if(postGroup.equals(PostGroup.FUNDING)){
				badgeIds.add(Long.valueOf(3));
			}

		}
		if(amount>=300000){
			if(postGroup.equals(PostGroup.DONATION)){
				badgeIds.add(Long.valueOf(11));
			}else if(postGroup.equals(PostGroup.FUNDING)){
				badgeIds.add(Long.valueOf(4));
			}
		}
	}

	public void cal(User user,List<Long>ids){
		for(Long id:ids) {
			UserBadge userBadge =
				userBadgeRepository.findByUserAndBadge(user, badgeRepository.findById(id)
					.orElseThrow(IllegalArgumentException::new));
			userBadge.set();
		}
	}


	@Override
	public void totalPayAmount(User user) {
		Long amount=paymentRepository.findAllByUser(user).stream().mapToLong(payment -> payment.getAmount()).sum();
		UserBadge userBadge=userBadgeRepository.findByUserAndBadge(user,badgeRepository.findById(Long.valueOf(1))
			.orElseThrow(IllegalArgumentException::new));
		if(amount>=1000000){
			userBadge.set();
		}
	}

	@Override
	public void teamFundingBadges(Team team) {
		List<Funding> fundingList=fundingRepository.findAllByTeamAndPostType(team, PostType.REPORT_ACCEPT);
		Integer count=fundingList.size();
		List<Long>ids=new ArrayList<>();
		if(count>=5){
			ids.add(Long.valueOf(15));
		}
		if(count>=10){
			ids.add(Long.valueOf(16));

		}
		if(count>=50){
			ids.add(Long.valueOf(17));
		}
		cal(team,ids);
	}
}
