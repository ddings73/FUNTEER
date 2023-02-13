package com.yam.funteer.funding.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.funding.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
	Optional<Report> findByFundingFundingId(Long fundingId);
}
