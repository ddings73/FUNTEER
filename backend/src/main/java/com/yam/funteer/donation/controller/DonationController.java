package com.yam.funteer.donation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.donation.exception.DonationNotFoundException;
import com.yam.funteer.donation.dto.request.DonationJoinReq;
import com.yam.funteer.donation.dto.request.DonationRegisterReq;
import com.yam.funteer.donation.service.DonationService;

import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(value="Donation",tags="도네이션")
@RestController
@RequestMapping("/donation")
@RequiredArgsConstructor

public class DonationController {

	private final DonationService donationService;
	@ApiOperation(value = "도네이션 리스트")
	@GetMapping("")
	public ResponseEntity<? extends BaseResponseBody> donationGetList() {
		donationService.donationGetList();
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

	@ApiOperation(value = "도네이션 상세", notes = "<strong>패스워드 필수<strong>.")
	@GetMapping("/{postId}")
	public ResponseEntity<? extends BaseResponseBody> donationGetDetail(@PathVariable Long postId){
		donationService.donationGetDetail(postId);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

	@ApiOperation(value = "도네이션 참여", notes = "<strong>userId, 금액 필수<strong>.")
	@PostMapping("/{postId}/pay")
	public ResponseEntity<? extends BaseResponseBody> donationJoin(@PathVariable Long postId,@RequestBody
		DonationJoinReq donationJoinReq)throws DonationNotFoundException{
		donationService.donationJoin(postId,donationJoinReq);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

	@ApiOperation(value = "도네이션 등록", notes = "<strong>userId,타이틀,내용,금액 필수<strong>.")
	@PostMapping("")
	public ResponseEntity<? extends BaseResponseBody> donationRegister(@RequestBody DonationRegisterReq donationRegisterReq){
		donationService.donationRegister(donationRegisterReq);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

	@ApiOperation(value = "도네이션 수정", notes = "<strong>postId, userId,타이틀,내용,금액 필수<strong>.")
	@PutMapping("/{postId}")
	public ResponseEntity<? extends BaseResponseBody> donationModify(@PathVariable Long postId,@RequestBody DonationRegisterReq donationRegisterReq) throws
		DonationNotFoundException {
		donationService.donationModify(postId,donationRegisterReq);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

	// @ApiOperation(value = "도네이션 삭제", notes = "<strong>postId, userId 필수<strong>.")
	// @DeleteMapping("/{postId}")
	// public ResponseEntity<? extends BaseResponseBody> donationDelete(@PathVariable Long postId,@RequestParam Long userId)throws DonationNotFoundException
	// {
	// 	donationService.donationDelete(postId,userId);
	// 	return ResponseEntity.ok(BaseResponseBody.of("Success"));
	// }
}
