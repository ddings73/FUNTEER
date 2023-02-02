package com.yam.funteer.funding.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yam.funteer.common.code.PostType;
import com.yam.funteer.funding.entity.Category;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.entity.TargetMoney;
import com.yam.funteer.post.entity.PostHashtag;
import com.yam.funteer.user.entity.Team;

public interface FundingRepository extends JpaRepository<Funding, Long> {
	List<Funding> findAllByPostType(PostType postType);

	List<Funding> findAllByCategory(Category category);

	List<Funding> findAllByTitleContainingOrContentContaining(String keyword, String keyword2);

	List<Funding> findAllByStartDate(LocalDate now);

	List<Funding> findAllByEndDate(LocalDate minusDays);

	List<Funding> findAllByTeamAAndPostType(Team team,PostType postType);
}
