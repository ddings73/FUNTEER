package com.yam.funteer.admin.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yam.funteer.admin.dto.MemberListResponse;
import com.yam.funteer.admin.dto.TeamConfirmRequest;
import com.yam.funteer.admin.dto.TeamListResponse;
import com.yam.funteer.admin.service.AdminService;
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.common.code.UserType;
import com.yam.funteer.funding.dto.request.RejectReasonRequest;
import com.yam.funteer.funding.dto.response.FundingListResponse;
import com.yam.funteer.funding.exception.FundingNotFoundException;
import com.yam.funteer.funding.service.FundingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@Api(tags ={"관리자"})
public class AdminController {

	private final AdminService adminService;
	private final FundingService fundingService;

	@ApiOperation(value = "개인 회원 목록 조회", notes = "개인 회원 목록을 조회한다.")
	@GetMapping("/members")
	public ResponseEntity<MemberListResponse> findAllMembers(@RequestParam(required = false, defaultValue = "") String keyword,
															 @RequestParam(required = false) UserType userType,
															 @PageableDefault(size = 8, sort = "id", direction = Sort.Direction.DESC)Pageable pageable) {
		MemberListResponse response = adminService.findMembersWithPageable(keyword, userType, pageable);
		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "단체 회원 목록 조회", notes = "단체 회원 목록을 조회한다.")
	@GetMapping("/team")
	public ResponseEntity<TeamListResponse> findAllTeam(@RequestParam(required = false, defaultValue = "") String keyword,
														@RequestParam(required = false) UserType userType,
														@PageableDefault(size = 8, sort = "id",  direction = Sort.Direction.DESC) Pageable pageable) {
		TeamListResponse response = adminService.findTeamWithPageable(keyword, userType, pageable);
		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "개인 회원 탈퇴 처리", notes = "개인 회원을 탈퇴 처리한다.")
	@DeleteMapping("/member/{memberId}")
	public ResponseEntity deleteMember(@PathVariable Long memberId) {
		adminService.resignMember(memberId);
		return ResponseEntity.ok("개인회원 탈퇴완료");
	}

	@ApiOperation(value = "단체 회원 탈퇴 처리", notes = "단체 회원을 탈퇴 처리한다.")
	@DeleteMapping("/team/{teamId}")
	public ResponseEntity<?> deleteTeam(@PathVariable Long teamId) {
		adminService.resignTeam(teamId);
		return ResponseEntity.ok("단체회원 탈퇴완료");
	}

	@ApiOperation(value = "단체회원 가입승인")
	@PostMapping("/team/{teamId}/accept")
	public ResponseEntity acceptTeam(@PathVariable Long teamId){
		adminService.acceptTeam(teamId);
		return ResponseEntity.ok("단체회원 가입승인");
	}

	@ApiOperation(value = "단체회원 가입거부", notes = "거절 시에는 거절메시지가 이메일로 전송.")
	@PutMapping("/team/{teamId}/reject")
	public ResponseEntity  rejectTeam(@PathVariable Long teamId, @RequestBody TeamConfirmRequest request){
		adminService.rejectTeam(teamId, request);
		return ResponseEntity.ok("처리가 완료되었습니다.");
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

	@ApiOperation(value = "관리자 펀딩 리스트 조회", notes = "관리자 페이지에서 펀딩 리스트를 조회한다.")
	@GetMapping("/funding")
	public ResponseEntity<Page<FundingListResponse>> findAllFunding(
			@RequestParam(required = false, defaultValue = "") String keyword,
			@RequestParam(required = false) PostType postType,
			@PageableDefault(size = 8, sort = "id",  direction = Sort.Direction.DESC) Pageable pageable) {
		return ResponseEntity.ok(fundingService.findAllFundingByAdmin(keyword, postType, pageable));
	}

	@ApiOperation(value = "관리자 펀딩 검색 조회", notes = "관리자 페이지에서 검색을 통해 제목과 내용에 키워드가 포함된 펀딩을 조회한다.")
	@GetMapping("/funding/search")
	public ResponseEntity<Page<FundingListResponse>> findFundingByKeyword(@RequestParam String keyword,
		@ApiParam(value = "PAGE 번호 (0부터)") @RequestParam(defaultValue = "0") int page,
		@ApiParam(value = "PAGE 크기") @RequestParam(defaultValue = "12") int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("regDate").descending());
		return ResponseEntity.ok(fundingService.findFundingByKeywordByAdmin(keyword, pageRequest));
	}

}
