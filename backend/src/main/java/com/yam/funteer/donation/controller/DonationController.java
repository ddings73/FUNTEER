package com.yam.funteer.donation.controller;

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
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.donation.dto.request.DonationModifyReq;
import com.yam.funteer.donation.dto.request.DonationStatusModify;
import com.yam.funteer.donation.exception.DonationNotFoundException;
import com.yam.funteer.donation.dto.request.DonationJoinReq;
import com.yam.funteer.donation.dto.request.DonationRegisterReq;
import com.yam.funteer.donation.service.DonationService;

import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(value="Donation",tags="도네이션")
@RestController
@RequestMapping("")
@RequiredArgsConstructor

public class DonationController {

	private final DonationService donationService;

	@ApiOperation(value="현재 진행 중인 도네이션")
	@GetMapping("/donation")
	public ResponseEntity<?> currentDonation() {
		return ResponseEntity.ok(donationService.donationGetCurrent());
	}

	@ApiOperation(value = "도네이션 리스트")
	@GetMapping(value={"/donation/list","/admin/donation"})
	public ResponseEntity<?> donationGetList( @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(donationService.donationGetList(page,size));
	}

	@ApiOperation(value = "도네이션 상세")
	@GetMapping("/donation/{postId}")
	public ResponseEntity<?> donationGetDetail(@PathVariable Long postId) throws
		DonationNotFoundException {
		return ResponseEntity.ok(donationService.donationGetDetail(postId));
	}

	@ApiOperation(value = "도네이션 참여", notes = "<strong>userId, 금액 필수<strong>.")
	@PostMapping("/donation/{postId}/pay")
	public ResponseEntity<? extends BaseResponseBody> donationJoin(@PathVariable Long postId,@RequestBody
		DonationJoinReq donationJoinReq)throws DonationNotFoundException{

		donationService.donationJoin(postId,donationJoinReq);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

	@ApiOperation(value="도네이션 상태만 변경")
	@PutMapping("/admin/donation")
	public ResponseEntity<?>donationStatusModify(@RequestBody DonationStatusModify donation){
		donationService.donationStatusModify(donation.getDonationId(),donation.getPostType());
		return ResponseEntity.ok(BaseResponseBody.of(donation.getDonationId()+" 번 도네이션 종료"));
	}

	@ApiOperation(value = "도네이션 등록", notes = "<strong>userId,타이틀,내용,금액 필수<strong>.")
	@PostMapping("/admin/donation")
	public ResponseEntity<?> donationRegister( DonationRegisterReq donationRegisterReq) {
		return ResponseEntity.ok(donationService.donationRegister(donationRegisterReq));
	}

	@ApiOperation(value = "도네이션 수정", notes = "<strong>postId, userId,타이틀,내용,금액, postType 필수<strong>.")
	@PutMapping("/admin/donation/{postId}")
	public ResponseEntity<?> donationModify(@PathVariable Long postId, DonationModifyReq donationModifyrReq) throws
		DonationNotFoundException{
		return ResponseEntity.ok(donationService.donationModify(postId,donationModifyrReq));
	}

}
