package com.yam.funteer.admin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yam.funteer.admin.dto.MemberListResponse;
import com.yam.funteer.admin.dto.TeamListResponse;
import com.yam.funteer.admin.service.AdminService;
import com.yam.funteer.funding.dto.request.RejectReasonRequest;
import com.yam.funteer.funding.dto.response.FundingDetailResponse;
import com.yam.funteer.funding.dto.response.FundingListResponse;
import com.yam.funteer.funding.exception.FundingNotFoundException;
import com.yam.funteer.funding.service.FundingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@Api(tags ={"관리자"})
public class 	AdminController {

	AdminService adminService;
	FundingService fundingService;

	@ApiOperation(value = "개인 회원 목록 조회", notes = "개인 회원 목록을 조회한다.")
	@GetMapping("/members")
	public ResponseEntity<List<MemberListResponse>> findAllMembers() {
		return null;
	}

	@ApiOperation(value = "단체 회원 목록 조회", notes = "단체 회원 목록을 조회한다.")
	@GetMapping("/team")
	public ResponseEntity<List<TeamListResponse>> findAllTeam() {
		return null;
	}

	@ApiOperation(value = "개인 회원 탈퇴 처리", notes = "개인 회원을 탈퇴 처리한다.")
	@DeleteMapping("/member")
	public ResponseEntity<?> deleteMember(@RequestParam Long memberId) {
		return null;
	}

	@ApiOperation(value = "단체 회원 탈퇴 처리", notes = "단체 회원을 탈퇴 처리한다.")
	@DeleteMapping("/team")
	public ResponseEntity<?> deleteTeam(@RequestParam Long teamId) {
		return null;
	}

	@ApiOperation(value = "펀딩 삭제", notes = "펀딩을 삭제한다.")
	@DeleteMapping("/funding/{fundingId}")
	public ResponseEntity<?> deleteFunding(@PathVariable Long fundingId) throws FundingNotFoundException {
		fundingService.deleteFunding(fundingId);
		return ResponseEntity.ok("펀딩 삭제 완료");
	}

	@ApiOperation(value = "펀딩 승인", notes = "제출된 펀딩을 확인하고 승인 상태로 바꾼다.")
	@PutMapping("/funding/{fundingId}/accept")
	public ResponseEntity<?> acceptFunding(@PathVariable Long fundingId) {
		adminService.acceptFunding(fundingId);
		return ResponseEntity.ok("펀딩 승인 완료");
	}

	@ApiOperation(value = "펀딩 거절", notes = "제출된 펀딩을 확인하고 거절 상태로 바꾼다.")
	@PutMapping("/funding/{fundingId}/reject")
	public ResponseEntity<?> rejectFunding(@PathVariable Long fundingId, @RequestBody RejectReasonRequest data) throws
		Exception {
		adminService.rejectFunding(fundingId, data);
		return ResponseEntity.ok("펀딩 승인이 거절되었습니다.");
	}

	@ApiOperation(value = "보고서 승인", notes = "제출된 보고서를 확인하고 승인 상태로 바꾼다.")
	@PutMapping("/funding/{fundingId}/report/accept")
	public ResponseEntity<?> acceptReport(@PathVariable Long fundingId) {
		adminService.acceptReport(fundingId);
		return ResponseEntity.ok("보고서 승인 완료");
	}

	@ApiOperation(value = "보고서 거절", notes = "제출된 보고서를 확인하고 거절 상태로 바꾼다.")
	@PutMapping("/funding/{fundingId}/report/reject")
	public ResponseEntity<?> rejectReport(@PathVariable Long fundingId, @RequestBody RejectReasonRequest data) throws
		Exception {
		adminService.rejectReport(fundingId, data);
		return ResponseEntity.ok("보고서 승인이 거절되었습니다.");
	}


}
