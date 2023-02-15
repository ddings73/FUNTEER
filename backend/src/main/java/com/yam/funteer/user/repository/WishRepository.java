package com.yam.funteer.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.user.entity.Member;
import com.yam.funteer.user.entity.Wish;

public interface WishRepository extends JpaRepository<Wish, Long> {
	Optional<Wish> findByMemberAndFunding(Member member, Funding funding);

	long countAllByMemberAndChecked(Member member, Boolean checked);

	long countAllByFundingIdAndChecked(Long fundingId, boolean b);

	List<Wish> findAllByFundingId(Long fundingId);
}
