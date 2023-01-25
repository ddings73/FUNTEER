package com.yam.funteer.donation.controller;

import java.util.List;

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

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.donation.entity.Donation;
import com.yam.funteer.donation.request.DonationGetListReq;
import com.yam.funteer.donation.request.DonationJoinReq;
import com.yam.funteer.donation.request.DonationRegisterReq;
import com.yam.funteer.donation.response.DonationGetListRes;
import com.yam.funteer.donation.service.DonationService;
import com.yam.funteer.pay.entity.Payment;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/donation")
@RequiredArgsConstructor
public class DonationController {

	private final DonationService donationService;

	@GetMapping("/")
	public ResponseEntity<? extends BaseResponseBody> donationGetList(@RequestBody DonationGetListReq donationGetListReq) {
		List<Donation> donationList=donationService.donationGetList(donationGetListReq);
		return ResponseEntity.status(200).body(DonationGetListRes.of("Success"));
	}

	@GetMapping("/{postId}")
	public ResponseEntity<? extends BaseResponseBody> donationGetDetail(@PathVariable Long postId){
		Donation donationDetail=donationService.donationGetDetail(postId);
		return ResponseEntity.status(200).body(DonationGetListRes.of("Success"));
	}

	@PostMapping("/{postId}/pay")
	public ResponseEntity<? extends BaseResponseBody> donationJoin(@PathVariable Long postId,@RequestBody DonationJoinReq donationJoinReq){
		Payment donationPayment=donationService.donationJoin(postId,donationJoinReq);
		return ResponseEntity.status(200).body(DonationGetListRes.of("Success"));
	}

	@PostMapping("/")
	public ResponseEntity<? extends BaseResponseBody> donationRegister(@RequestBody DonationRegisterReq donationRegisterReq){

		Donation donationRegisterpost=donationService.donationRegister(donationRegisterReq);
		return ResponseEntity.status(200).body(DonationGetListRes.of("Success"));
	}

	@PutMapping("/{postId}")
	public ResponseEntity<? extends BaseResponseBody> donationModify(@PathVariable Long postId,@RequestBody DonationRegisterReq donationRegisterReq){
		Boolean donationmodifyResult=donationService.donationModify(postId,donationRegisterReq);
		if(donationmodifyResult) return ResponseEntity.status(200).body(DonationGetListRes.of("Success"));
		return ResponseEntity.status(404).body(BaseResponseBody.of("Not Found"));
	}

	@DeleteMapping("/{postId}")
	public ResponseEntity<? extends BaseResponseBody> donationDelete(@PathVariable Long postId,@RequestParam Long adminId){
		Boolean donationDeleteResult=donationService.donationDelete(postId, adminId);
		if(donationDeleteResult) return ResponseEntity.status(200).body(DonationGetListRes.of("Success"));
		return ResponseEntity.status(404).body(BaseResponseBody.of("Not Found"));

	}
}
