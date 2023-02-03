package com.yam.funteer.funding.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

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

import com.yam.funteer.common.aws.AwsS3Uploader;
import com.yam.funteer.funding.dto.request.FundingCommentRequest;
import com.yam.funteer.funding.dto.response.FundingDetailResponse;
import com.yam.funteer.funding.dto.response.FundingListPageResponse;
import com.yam.funteer.funding.dto.response.FundingListResponse;
import com.yam.funteer.funding.dto.request.FundingReportRequest;
import com.yam.funteer.funding.dto.response.FundingReportResponse;
import com.yam.funteer.funding.dto.request.FundingRequest;
import com.yam.funteer.funding.dto.request.RejectReasonRequest;
import com.yam.funteer.funding.dto.request.TakeFundingRequest;
import com.yam.funteer.funding.exception.CommentNotFoundException;
import com.yam.funteer.funding.exception.FundingNotFoundException;
import com.yam.funteer.funding.service.FundingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/funding")
@Api(tags ={"펀딩"})
public class FundingController {

	private final FundingService fundingService;
	private final AwsS3Uploader awsS3Uploader;


	@ApiOperation(value = "펀딩 리스트 조회", notes = "펀딩 리스트를 조회한다.")
	@GetMapping("")
	public ResponseEntity<FundingListPageResponse> findAllFunding() {
		return ResponseEntity.ok(fundingService.findAllFunding());
	}

	@ApiOperation(value = "펀딩 검색 조회", notes = "검색을 통해 제목과 내용에 키워드가 포함된 펀딩을 조회한다.")
	@GetMapping("/search")
	public ResponseEntity<List<FundingListResponse>> findFundingByKeyword(@RequestParam String keyword) {
		return ResponseEntity.ok(fundingService.findFundingByKeyword(keyword));
	}

	@ApiOperation(value = "해시태그별 펀딩 조회", notes = "해시태그별 펀딩 목록을 조회한다.")
	@GetMapping("/hasgtag")
	private ResponseEntity<List<FundingListResponse>> findFundingByHashtag(@RequestParam String hashtag) {
		return ResponseEntity.ok(fundingService.findFundingByHashtag(hashtag));
	}

	@ApiOperation(value = "카테고리별 펀딩 리스트 조회", notes = "카테고리별 펀딩 리스트를 조회한다.")
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<FundingListResponse>> findFundingByCategory(@PathVariable Long categoryId) {
		return ResponseEntity.ok(fundingService.findFundingByCategory(categoryId));
	}

	@ApiOperation(value = "펀딩 생성 시 s3에 파일업로드", notes = "펀딩 생성 시 s3에 파일을 업로드 한다.")
	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
		String fileName = awsS3Uploader.upload(multipartFile, "FundingDetailFiles");
		return fileName;
	}


	@ApiOperation(value = "펀딩 생성", notes = "새로운 펀딩 게시글을 생성한다.")
	@PostMapping("")
	public  ResponseEntity<?> createFunding(MultipartFile thumbnail, @Valid FundingRequest data) throws IOException {
		FundingDetailResponse funding = fundingService.createFunding(thumbnail, data);
		return ResponseEntity.ok(funding);
	}

	@ApiOperation(value = "펀딩 상세 조회", notes = "펀딩 게시글 상세를 조회한다.")
	@GetMapping("/{fundingId}")
	public ResponseEntity<FundingDetailResponse> readFundingDetail(@PathVariable Long fundingId) {
		return ResponseEntity.ok(fundingService.findFundingById(fundingId));
	}

	@ApiOperation(value = "펀딩 게시글 수정", notes = "펀딩 게시글을 수정한다.")
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
	public ResponseEntity<FundingReportResponse> createFundingReport(@PathVariable Long fundingId, FundingReportRequest data) {
		FundingReportResponse fundingReport = fundingService.createFundingReport(fundingId, data);
		return ResponseEntity.ok(fundingReport);
	}

	@ApiOperation(value = "펀딩 게시글 보고서 탭 조회", notes = "펀딩 게시글 보고서 탭을 조회한다.")
	@GetMapping("/{fundingId}/report")
	public ResponseEntity<FundingReportResponse> readFundingReport(@PathVariable Long fundingId) {
		return ResponseEntity.ok(fundingService.findFundingReportById(fundingId));
	}

	@ApiOperation(value = "펀딩 게시글 보고서 수정", notes = "펀딩 게시글 보고서를 수정한다.")
	@PutMapping("/{fundingId}/report")
	public ResponseEntity<FundingReportResponse> updateFundingReport(@PathVariable Long fundingId, FundingReportRequest data) {
		return ResponseEntity.ok(fundingService.updateFundingReport(fundingId, data));
	}

	@ApiOperation(value = "펀딩 참여", notes = "회원이 펀딩에 참여한다.")
	@PostMapping("/{fundingId}/pay")
	public ResponseEntity<?> takeFunding(@PathVariable Long fundingId, TakeFundingRequest data) {
		fundingService.takeFunding(fundingId, data);
		return ResponseEntity.ok("펀딩 참여 완료");
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
