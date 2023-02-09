package com.yam.funteer.admin.service;

import com.yam.funteer.funding.dto.request.RejectReasonRequest;

public interface AdminService {

	void acceptFunding(Long fundingId);

	String rejectFunding(Long fundingId, RejectReasonRequest data) throws Exception;

	void acceptReport(Long fundingId);

	String  rejectReport(Long fundingId, RejectReasonRequest data) throws Exception;

}
