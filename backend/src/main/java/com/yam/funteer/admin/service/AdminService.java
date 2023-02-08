package com.yam.funteer.admin.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.yam.funteer.admin.dto.MemberListResponse;
import com.yam.funteer.admin.dto.TeamFileConfirmRequest;
import com.yam.funteer.admin.dto.TeamListResponse;
import com.yam.funteer.funding.dto.request.RejectReasonRequest;

public interface AdminService {

	MemberListResponse findMembersWithPageable(String keyword, Pageable pageable);

	TeamListResponse findTeamWithPageable(String keyword, Pageable pageable);

	void resignMember(Long memberId);

	void resignTeam(Long teamId);

	void rejectTeam(Long teamId, TeamFileConfirmRequest request);

	void acceptFunding(Long fundingId);

	String rejectFunding(Long fundingId, RejectReasonRequest data) throws Exception;

	void acceptReport(Long fundingId);

	String  rejectReport(Long fundingId, RejectReasonRequest data) throws Exception;
}
