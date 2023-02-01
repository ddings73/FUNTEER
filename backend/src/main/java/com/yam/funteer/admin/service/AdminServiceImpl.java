package com.yam.funteer.admin.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.funding.dto.request.RejectReasonRequest;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.entity.Report;
import com.yam.funteer.funding.repository.FundingRepository;
import com.yam.funteer.funding.repository.ReportRepository;
import com.yam.funteer.mail.service.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

	private final FundingRepository fundingRepository;
	private final EmailService emailService;
	private final ReportRepository reportRepository;


	@Override
	public void acceptFunding(Long fundingId) {
		Funding funding = fundingRepository.findById(fundingId).orElseThrow();
		funding.setPostType(PostType.FUNDING_ACCEPT);
	}

	@Override
	public String rejectFunding(Long fundingId, RejectReasonRequest data) throws Exception {
		Funding funding = fundingRepository.findById(fundingId).orElseThrow();
		funding.setPostType(PostType.FUNDING_REJECT);
		funding.setRejectComment(data.getRejectReason());
		emailService.sendRejectMessage(funding.getTeam().getEmail(), data.getRejectReason(), PostGroup.FUNDING);
		return data.getRejectReason();
	}

	@Override
	public void acceptReport(Long fundingId) {
		Funding funding = fundingRepository.findById(fundingId).orElseThrow();
		funding.setPostType(PostType.REPORT_ACCEPT);
	}

	@Override
	public String rejectReport(Long fundingId, RejectReasonRequest data) throws Exception {
		Funding funding = fundingRepository.findById(fundingId).orElseThrow();
		Report report = reportRepository.findByFundingId(fundingId);
		funding.setPostType(PostType.REPORT_REJECT);
		report.setReportRejectComment(data.getRejectReason());
		emailService.sendRejectMessage(funding.getTeam().getEmail(), data.getRejectReason(), PostGroup.REPORT);
		return data.getRejectReason();
	}

}
