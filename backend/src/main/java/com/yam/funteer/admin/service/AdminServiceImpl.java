package com.yam.funteer.admin.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yam.funteer.admin.dto.MemberListResponse;
import com.yam.funteer.admin.dto.TeamConfirmRequest;
import com.yam.funteer.admin.dto.TeamListResponse;
import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.attach.entity.TeamAttach;
import com.yam.funteer.attach.repository.TeamAttachRepository;
import com.yam.funteer.badge.service.BadgeService;
import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.funding.dto.request.RejectReasonRequest;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.entity.Report;
import com.yam.funteer.funding.repository.FundingRepository;
import com.yam.funteer.funding.repository.ReportRepository;
import com.yam.funteer.mail.service.EmailService;
import com.yam.funteer.user.entity.Member;
import com.yam.funteer.user.entity.Team;
import com.yam.funteer.user.repository.MemberRepository;
import com.yam.funteer.user.repository.TeamRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
	private final MemberRepository memberRepository;
	private final TeamRepository teamRepository;
	private final TeamAttachRepository teamAttachRepository;
	private final FundingRepository fundingRepository;
	private final EmailService emailService;
	private final ReportRepository reportRepository;
	private final BadgeService badgeService;

	@Override
	public MemberListResponse findMembersWithPageable(String keyword, Pageable pageable) {
		Page<Member> memberPage = memberRepository.findAllByNicknameContaining(keyword, pageable);
		return MemberListResponse.of(memberPage);
	}

	@Override
	public TeamListResponse findTeamWithPageable(String keyword, Pageable pageable) {

		Page<Team> teamPage = teamRepository.findAllByNameContaining(keyword, pageable);

		List<TeamListResponse.TeamInfo> list = teamPage.stream().map(team -> {
			List<TeamAttach> teamAttachList = teamAttachRepository.findAllByTeam(team);
			String vmsFilePath = null, perFormFilePath = null;
			for(TeamAttach teamAttach : teamAttachList){
				Attach attach = teamAttach.getAttach();
				switch(attach.getFileType()){
					case VMS: vmsFilePath = attach.getPath(); break;
					case PERFORM: perFormFilePath = attach.getPath(); break;
					default: break;
				}
			};
			return TeamListResponse.TeamInfo.of(team, vmsFilePath, perFormFilePath);
		}).collect(Collectors.toList());

		return TeamListResponse.of(teamPage, list);
	}

	@Override
	public void resignMember(Long memberId) {
		Member member = memberRepository.findById(memberId).orElseThrow(UserNotFoundException::new);
		member.signOut();
	}

	@Override
	public void resignTeam(Long teamId) {
		Team team = teamRepository.findById(teamId).orElseThrow(UserNotFoundException::new);
		team.signOut();
	}

	@Override
	public void acceptTeam(Long teamId) {
		Team team = teamRepository.findById(teamId).orElseThrow(UserNotFoundException::new);
		team.accept();
	}

	// 미완성
	@Override
	public void rejectTeam(Long teamId, TeamConfirmRequest request) {
		Team team = teamRepository.findById(teamId).orElseThrow(UserNotFoundException::new);

	}


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
		log.info("fundingId => {}", fundingId);
		Funding funding = fundingRepository.findById(fundingId).orElseThrow();
		log.info("funding => {}", funding);
		Team team = teamRepository.findById(funding.getTeam().getId()).orElseThrow();
		log.info("team => {}", team);
		team.addTotalFundingAmount(funding.getCurrentFundingAmount());
		funding.setPostType(PostType.REPORT_ACCEPT);
		badgeService.teamFundingBadges(funding.getTeam());
	}

	@Override
	public String rejectReport(Long fundingId, RejectReasonRequest data) throws Exception {
		Funding funding = fundingRepository.findById(fundingId).orElseThrow();
		Report report = reportRepository.findByFundingFundingId(fundingId);
		funding.setPostType(PostType.REPORT_REJECT);
		report.setReportRejectComment(data.getRejectReason());
		emailService.sendRejectMessage(funding.getTeam().getEmail(), data.getRejectReason(), PostGroup.REPORT);
		return data.getRejectReason();
	}

}
