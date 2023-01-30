package com.yam.funteer.funding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.common.code.PostType;
import com.yam.funteer.funding.entity.Funding;

public interface FundingRepository extends JpaRepository<Funding, Long> {
	List<Funding> findAllByPostType(PostType postType);

}
