package com.yam.funteer.funding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.common.code.TargetMoneyType;
import com.yam.funteer.funding.dto.response.TargetMoneyResponse;
import com.yam.funteer.funding.entity.TargetMoney;

public interface TargetMoneyRepository extends JpaRepository<TargetMoney, Long> {
	List<TargetMoneyResponse> findAllByFundingIdAndTargetMoneyType(Long id, TargetMoneyType level);

}
