package com.yam.funteer.admin.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.yam.funteer.admin.dto.MemberListResponse;
import com.yam.funteer.admin.dto.TeamListResponse;
import com.yam.funteer.funding.dto.request.RejectReasonRequest;

public interface AdminService {

	List<MemberListResponse> findMembersWithPageable(Pageable pageable);

	List<TeamListResponse> findTeamWithPageable(Pageable pageable);

	void acceptFunding(Long fundingId);

	String rejectFunding(Long fundingId, RejectReasonRequest data) throws Exception;

	void acceptReport(Long fundingId);

	String  rejectReport(Long fundingId, RejectReasonRequest data) throws Exception;
}
