package com.yam.funteer.admin.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.data.domain.Pageable;

import com.yam.funteer.admin.dto.MemberListResponse;
import com.yam.funteer.admin.dto.TeamConfirmRequest;
import com.yam.funteer.admin.dto.TeamListResponse;
import com.yam.funteer.common.code.UserType;
import com.yam.funteer.funding.dto.request.RejectReasonRequest;

public interface AdminService {

	MemberListResponse findMembersWithPageable(String keyword, UserType userType, Pageable pageable);

	TeamListResponse findTeamWithPageable(String keyword, UserType userType, Pageable pageable);



	void resignMember(Long memberId);

	void resignTeam(Long teamId);

	void acceptTeam(Long teamId);

	void rejectTeam(Long teamId, TeamConfirmRequest request);

	void acceptFunding(Long fundingId);

	void rejectFunding(Long fundingId, RejectReasonRequest data) throws Exception;

	void acceptReport(Long fundingId);

	void rejectReport(Long fundingId, RejectReasonRequest data) throws MessagingException, UnsupportedEncodingException;
}
