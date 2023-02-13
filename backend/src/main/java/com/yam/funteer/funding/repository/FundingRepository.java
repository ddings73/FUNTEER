package com.yam.funteer.funding.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yam.funteer.common.code.PostType;
import com.yam.funteer.funding.entity.Category;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.user.entity.Team;

public interface FundingRepository extends JpaRepository<Funding, Long> {

	List<Funding> findAllByPostType(PostType postType);

	List<Funding> findAllByCategory(Category category);

	List<Funding> findAllByTitleContainingOrContentContaining(String keyword, String keyword2);

	Page<Funding> findAllByTitleContainingOrContentContaining(String keyword, String keyword2, Pageable pageable);

	List<Funding> findAllByStartDate(LocalDate now);

	List<Funding> findAllByEndDate(LocalDate minusDays);

	List<Funding> findAllByTeamAndPostType(Team team,PostType postType);

	Page<Funding> findAllByTeam(Team team, Pageable pageable);

	Optional<Funding> findByFundingId(Long fundingId);

	@Modifying
	@Query("update Funding set hit = hit + 1 where fundingId = :fundingId")
	int updateHit(@Param(value = "fundingId") Long fundingId);
}
