package com.yam.funteer.badge.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yam.funteer.badge.entity.Badge;
import com.yam.funteer.badge.repository.BadgeRepository;
import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.common.code.UserType;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.repository.FundingRepository;
import com.yam.funteer.pay.entity.Payment;
import com.yam.funteer.pay.repository.PaymentRepository;
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
	public void initBadges(User user) {
		if(user.getUserType().equals(UserType.TEAM_WAIT)){
			List<Badge> badgeList=badgeRepository.findAllByIdBetween(9L, 17L);
			for(Badge badge:badgeList){
				UserBadge userBadge=UserBadge.builder()
					.user(user)
					.badge(badge)
					.achieve(false).build();
				userBadgeRepository.save(userBadge);
			}
		}else if(!user.getUserType().equals(UserType.ADMIN)){
			List<Badge> badgeList=badgeRepository.findAllByIdBetween(1L, 14L);
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
				badgeIds.add(12L);
			}else if(postGroup.equals(PostGroup.FUNDING)){
				badgeIds.add(5L);
			}
		}
		if( count>=10) {
			if(postGroup.equals(PostGroup.DONATION)){
				badgeIds.add(13L);
			}else if(postGroup.equals(PostGroup.FUNDING)){
				badgeIds.add(6L);
			}

		}
		if(count>=30){
			if(postGroup.equals(PostGroup.DONATION)){
				badgeIds.add(14L);
			}else if(postGroup.equals(PostGroup.FUNDING)){
				badgeIds.add(7L);
			}
		}
	}

	private void amountBadge(PostGroup postGroup, List<Payment> paymentList, List<Long> badgeIds) {
		Long amount=paymentList.stream().mapToLong(payment -> payment.getAmount()).sum();
		if ( amount>=50000){
			if(postGroup.equals(PostGroup.DONATION)){
				badgeIds.add(9L);
			}else if(postGroup.equals(PostGroup.FUNDING)){
				badgeIds.add(2L);
			}
		}
		if( amount>=100000) {
			if(postGroup.equals(PostGroup.DONATION)){
				badgeIds.add(10L);
			}else if(postGroup.equals(PostGroup.FUNDING)){
				badgeIds.add(3L);
			}

		}
		if(amount>=300000){
			if(postGroup.equals(PostGroup.DONATION)){
				badgeIds.add(11L);
			}else if(postGroup.equals(PostGroup.FUNDING)){
				badgeIds.add(4L);
			}
		}
	}

	private void cal(User user,List<Long>ids){
		for(Long id:ids) {
			UserBadge userBadge =
				userBadgeRepository.findByUserAndBadge(user, badgeRepository.findById(id)
					.orElseThrow(IllegalArgumentException::new));
			userBadge.achieve();
		}
	}


	@Override
	public void totalPayAmount(User user) {
		Long amount=paymentRepository.findAllByUser(user).stream().mapToLong(payment -> payment.getAmount()).sum();
		UserBadge userBadge=userBadgeRepository.findByUserAndBadge(user,badgeRepository.findById(1L)
			.orElseThrow(IllegalArgumentException::new));
		if(amount>=1000000){
			userBadge.achieve();
		}
	}

	@Override
	public void teamFundingBadges(Team team) {
		List<Funding> fundingList=fundingRepository.findAllByTeamAndPostType(team, PostType.REPORT_ACCEPT);
		Integer count=fundingList.size();
		List<Long>ids=new ArrayList<>();
		if(count>=5){
			ids.add(15L);
		}
		if(count>=10){
			ids.add(16L);
		}
		if(count>=50){
			ids.add(17L);
		}
		cal(team,ids);
	}
}
