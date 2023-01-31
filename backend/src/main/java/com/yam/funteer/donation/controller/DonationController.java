package com.yam.funteer.donation.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.common.aws.AwsS3Uploader;
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
	public ResponseEntity<?> donationGetList() {
		return ResponseEntity.ok(donationService.donationGetList());
	}

	@ApiOperation(value = "도네이션 상세")
	@GetMapping("/{postId}")
	public ResponseEntity<?> donationGetDetail(@PathVariable Long postId) throws
		DonationNotFoundException {

		return ResponseEntity.ok(donationService.donationGetDetail(postId));
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
	public ResponseEntity<?> donationRegister( DonationRegisterReq donationRegisterReq) {
		return ResponseEntity.ok(donationService.donationRegister(donationRegisterReq));
	}

	@ApiOperation(value = "도네이션 수정", notes = "<strong>postId, userId,타이틀,내용,금액 필수<strong>.")
	@PutMapping("/{postId}")
	public ResponseEntity<?> donationModify(@PathVariable Long postId,DonationRegisterReq donationModifyrReq) throws
		DonationNotFoundException{
		return ResponseEntity.ok(donationService.donationModify(postId,donationModifyrReq));
	}

}
