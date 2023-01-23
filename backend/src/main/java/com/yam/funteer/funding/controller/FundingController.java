package com.yam.funteer.funding.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yam.funteer.code.GroupCode;
import com.yam.funteer.funding.dto.FundingDetailResponse;
import com.yam.funteer.funding.dto.FundingListResponse;
import com.yam.funteer.funding.dto.FundingRequest;
import com.yam.funteer.funding.service.FundingService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/funding")
public class FundingController {

	private final FundingService fundingService;

	@ApiOperation(value = "펀딩 리스트 조회", notes = "펀딩 리스트를 조회한다.")
	@GetMapping("/")
	public ResponseEntity<List<FundingListResponse>> findAllFundings() {
		return ResponseEntity.ok(fundingService.findAllFundings(GroupCode.A01));
	}

	@ApiOperation(value = "펀딩 생성", notes = "새로운 펀딩 게시글을 생성한다.")
	@PostMapping("/")
	public  ResponseEntity<?> createFunding(@RequestBody FundingRequest data) {
		fundingService.createFunding(data);
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "펀딩 상세 조회", notes = "펀딩 게시글 상세를 조회한다.")
	@GetMapping("/{fundingId}")
	public ResponseEntity<FundingDetailResponse> readFundingDetail(@PathVariable Long fundingId) {
		return ResponseEntity.ok(fundingService.findFundingById(fundingId));
	}

	@ApiOperation(value = "펀딩 게시글 수정", notes = "펀딩 게시글을 수정한다. (D12: 승인 거절 시 게시글 전체 수정 가능 / D14: 진행중일때 기간 1회 수정 가능 / D15: 진행중, 수정 불가능")
	@PutMapping("/{fundingId}/report")
	public ResponseEntity<FundingDetailResponse> updateFunding(@PathVariable Long fundingId, @RequestBody FundingRequest data) {
		return ResponseEntity.ok(fundingService.updateFunding(fundingId, data));
	}
}
