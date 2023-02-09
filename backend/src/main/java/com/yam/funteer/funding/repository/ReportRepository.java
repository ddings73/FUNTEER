package com.yam.funteer.funding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.funding.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
	Report findByFundingFundingId(Long fundingId);
}
