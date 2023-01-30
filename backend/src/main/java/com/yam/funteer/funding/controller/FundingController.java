package com.yam.funteer.funding.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.yam.funteer.funding.dto.FundingCommentRequest;
import com.yam.funteer.funding.dto.FundingDetailResponse;
import com.yam.funteer.funding.dto.FundingListResponse;
import com.yam.funteer.funding.dto.FundingReportRequest;
import com.yam.funteer.funding.dto.FundingReportResponse;
import com.yam.funteer.funding.dto.FundingRequest;
import com.yam.funteer.funding.dto.TakeFundingRequest;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.exception.CommentNotFoundException;
import com.yam.funteer.funding.exception.FundingNotFoundException;
import com.yam.funteer.funding.service.FundingService;
import com.yam.funteer.post.repository.CommentRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/funding")
@Api(tags ={"펀딩"})
public class FundingController {

	private final FundingService fundingService;

	@ApiOperation(value = "진행중인 펀딩 리스트 조회", notes = "진행중인 펀딩 리스트를 조회한다.")
	@GetMapping("/")
	public ResponseEntity<List<FundingListResponse>> findInProgressFunding() {
		return ResponseEntity.ok(fundingService.findInProgressFunding());
	}

	// @ApiOperation(value = "종료된 펀딩 리스트 조회", notes = "종료된 펀딩 리스트를 조회한다.")
	// @GetMapping("/")
	// public ResponseEntity<List<FundingListResponse>> findCompleteFunding() {
	// 	return ResponseEntity.ok(fundingService.findCompleteFunding());
	// }
	//
	// @ApiOperation(value = "카테고리별 진행중인 펀딩 리스트 조회", notes = "카테고리별 진행중인 펀딩 리스트를 조회한다.")
	// @GetMapping("/")
	// public ResponseEntity<List<FundingListResponse>> findInProgressFundingByCategory() {
	// 	return ResponseEntity.ok(fundingService.findInProgressFundingByCategory());
	// }
	//
	// @ApiOperation(value = "검색 키워드로 진행중인 펀딩 리스트 조회", notes = "검색 키워드로 진행중인 펀딩 리스트를 조회한다.")
	// @GetMapping("/")
	// public ResponseEntity<List<FundingListResponse>> findInProgressFundingByKeyword() {
	// 	return ResponseEntity.ok(fundingService.findInProgressFundingByKeyword());
	// }
	//

	@ApiOperation(value = "펀딩 리스트 조회", notes = "펀딩 리스트를 조회한다.")
	@GetMapping("/test")
	public ResponseEntity<List<FundingListResponse>> findAllFunding() {
		return ResponseEntity.ok(fundingService.findAllFunding());
	}

	@ApiOperation(value = "펀딩 생성", notes = "새로운 펀딩 게시글을 생성한다.")
	@PostMapping("/")
	public  ResponseEntity<?> createFunding(MultipartFile thumbnail, FundingRequest data) throws IOException {
		FundingDetailResponse funding = fundingService.createFunding(thumbnail, data);
		return ResponseEntity.ok(funding);
	}

	@ApiOperation(value = "펀딩 상세 조회", notes = "펀딩 게시글 상세를 조회한다.")
	@GetMapping("/{fundingId}")
	public ResponseEntity<FundingDetailResponse> readFundingDetail(@PathVariable Long fundingId) {
		return ResponseEntity.ok(fundingService.findFundingById(fundingId));
	}

	@ApiOperation(value = "펀딩 게시글 수정", notes = "펀딩 게시글을 수정한다. (D12: 승인 거절 시 게시글 전체 수정 가능 / D14: 진행중일때 기간 1회 수정 가능 / D15: 진행중, 수정 불가능")
	@PutMapping("/{fundingId}")
	public ResponseEntity<FundingDetailResponse> updateFunding(@PathVariable Long fundingId, MultipartFile thumbnail,
		FundingRequest data) throws Exception {
		return ResponseEntity.ok(fundingService.updateFunding(fundingId, thumbnail, data));
	}

	@ApiOperation(value = "펀딩 게시글 삭제", notes = "펀딩 게시글을 삭제한다.")
	@DeleteMapping("/{fundingId}")
	public ResponseEntity<?> deleteFunding(@PathVariable Long fundingId) throws FundingNotFoundException {
		fundingService.deleteFunding(fundingId);
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "펀딩 게시글 보고서 작성", notes = "펀딩 게시글 보고서를 작성한다.")
	@PostMapping("/{fundingId}/report")
	public ResponseEntity<?> createFundingReport(@PathVariable Long fundingId, @RequestBody FundingReportRequest data) {
		fundingService.createFundingReport(data);
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "펀딩 게시글 보고서 탭 조회", notes = "펀딩 게시글 보고서 탭을 조회한다.")
	@GetMapping("/{fundingId}/report")
	public ResponseEntity<FundingReportResponse> readFundingReport(@PathVariable Long fundingId) {
		return ResponseEntity.ok(fundingService.findFundingReportById(fundingId));
	}

	@ApiOperation(value = "펀딩 게시글 보고서 수정", notes = "펀딩 게시글 보고서를 수정한다.")
	@PutMapping("/{fundingId}/report")
	public ResponseEntity<FundingReportResponse> updateFundingReport(@PathVariable Long fundingId, @RequestBody FundingReportResponse data) {
		return ResponseEntity.ok(fundingService.updateFundingReport(fundingId, data));
	}

	@ApiOperation(value = "펀딩 참여", notes = "회원이 펀딩에 참여한다.")
	@PostMapping("/{fundingId}/pay")
	public ResponseEntity<?> takeFunding(@PathVariable Long fundingId, TakeFundingRequest data) {
		fundingService.takeFunding(fundingId, data);
		return null;
	}

	@ApiOperation(value = "펀딩 응원 댓글", notes = "펀딩 게시글에 응원 댓글을 작성한다.")
	@PostMapping("/{fundingId}/comment")
	public ResponseEntity<?> createFundingComment(@PathVariable Long fundingId, @RequestBody FundingCommentRequest data) {
		fundingService.createFundingComment(fundingId, data);
		return ResponseEntity.ok("CommentCreated Well !");
	}

	@ApiOperation(value = "펀딩 댓글 삭제", notes = "펀딩 게시글의 댓글을 삭제한다.")
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<?> deleteFundingComment(@PathVariable Long commentId) throws CommentNotFoundException {
		fundingService.deleteFundingComment(commentId);
		return ResponseEntity.ok("삭제 완료");
	}
}
