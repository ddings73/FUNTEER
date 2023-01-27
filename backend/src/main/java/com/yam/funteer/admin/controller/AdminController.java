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
import com.yam.funteer.funding.dto.FundingDetailResponse;
import com.yam.funteer.funding.dto.FundingListResponse;
import com.yam.funteer.funding.dto.FundingRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@Api(tags ={"관리자"})
public class AdminController {

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

	@ApiOperation(value = "모든 펀딩 목록 조회", notes = "모든 펀딩 목록을 조회한다.")
	@GetMapping("/funding")
	public ResponseEntity<List<FundingListResponse>> findAllFunding() {
		return null;
	}

	@ApiOperation(value = "승인 대기 펀딩 목록 조회", notes = "승인 대기중인 펀딩 목록을 조회한다.")
	@GetMapping("/funding/wait")
	public ResponseEntity<List<FundingListResponse>> findWaitFundingList() {
		return null;
	}

	@ApiOperation(value = "펀딩 상세 조회", notes = "펀딩 상세를 조회한다.")
	@GetMapping("/funding/{fundingId}")
	public ResponseEntity<FundingDetailResponse> readFundingDetail(@PathVariable Long fundingId) {
		return null;
	}

	@ApiOperation(value = "펀딩 삭제", notes = "펀딩을 삭제한다.")
	@DeleteMapping("/funding/{fundingId}")
	public ResponseEntity<?> deleteFunding(@PathVariable Long fundingId) {
		return null;
	}


}
