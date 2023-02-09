package com.yam.funteer.user.repository;

import java.util.List;

import com.yam.funteer.user.entity.Charge;
import com.yam.funteer.user.entity.Member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargeRepository extends JpaRepository<Charge, Long> {
	Charge findByPayImpUid(String impUid);

	List<Charge> findAllByPossibleRefund(int b);

	Page<Charge> findAllByMemberId(Long memberId, Pageable pageable);
}
