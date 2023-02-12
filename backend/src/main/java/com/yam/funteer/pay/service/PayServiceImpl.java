package com.yam.funteer.pay.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.yam.funteer.common.security.SecurityUtil;
import com.yam.funteer.pay.dto.CancelRequest;
import com.yam.funteer.pay.exception.ImpossibleRefundException;
import com.yam.funteer.user.entity.Charge;
import com.yam.funteer.user.entity.Member;
import com.yam.funteer.user.repository.ChargeRepository;
import com.yam.funteer.user.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PayServiceImpl implements PayService {

	private final MemberRepository memberRepository;
	private final ChargeRepository chargeRepository;

	@Override
	public void refundMileage(CancelRequest cancelRequest) throws ImpossibleRefundException {

		Member member = memberRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow();
		Charge charge = chargeRepository.findByPayImpUid(cancelRequest.getImp_uid());

		if (member.getMoney() < charge.getAmount() || charge.getPossibleRefund() == 0 || charge.getMember() != member)  {
			throw new ImpossibleRefundException("환불이 불가능합니다.");
		}

		charge.setPossibleRefund();
		charge.setRefundReason(charge.getRefundReason());
		member.setMoney(member.getMoney() - charge.getAmount());

	}

	@Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
	public void changeStatusCharge() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime seven = now.minusDays(7);

		List<Charge> all = chargeRepository.findAllByPossibleRefund(1);
		for (Charge charge : all) {
			int resultDate = seven.compareTo(charge.getChargeDate());
			if (resultDate > 0) {
				charge.setPossibleRefund();
			}
		}
	}
}
