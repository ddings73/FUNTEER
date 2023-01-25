package com.yam.funteer.funding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.funding.entity.Funding;

public interface FundingRepository extends JpaRepository<Funding, Long> {
}
