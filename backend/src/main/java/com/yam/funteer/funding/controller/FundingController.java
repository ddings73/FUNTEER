package com.yam.funteer.funding.controller;

import java.io.IOException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.yam.funteer.common.aws.AwsS3Uploader;
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.funding.dto.request.FundingCommentRequest;
import com.yam.funteer.funding.dto.response.FundingDetailResponse;
import com.yam.funteer.funding.dto.response.FundingListPageResponse;
import com.yam.funteer.funding.dto.response.FundingListResponse;
import com.yam.funteer.funding.dto.request.FundingReportRequest;
import com.yam.funteer.funding.dto.response.FundingReportResponse;
import com.yam.funteer.funding.dto.request.FundingRequest;
import com.yam.funteer.funding.dto.request.TakeFundingRequest;
import com.yam.funteer.funding.exception.CommentNotFoundException;
import com.yam.funteer.funding.exception.FundingNotFoundException;
import com.yam.funteer.funding.exception.NotAuthenticatedMemberException;
import com.yam.funteer.funding.exception.NotAuthenticatedTeamException;
import com.yam.funteer.funding.service.FundingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/funding")
@Api(tags ={"펀딩"}) @Slf4j
public class FundingController {

	private final FundingService fundingService;
	private final AwsS3Uploader awsS3Uploader;


	@ApiOperation(value = "펀딩 리스트 조회", notes = "펀딩 리스트를 조회한다.")
	@GetMapping("")
	public ResponseEntity<FundingListPageResponse> findAllFunding(
		@PageableDefault(size = 12, sort = "id",  direction = Sort.Direction.DESC) Pageable pageable,
		@RequestParam(required = false, defaultValue = "") String keyword,
		@RequestParam(required = false) PostType postType,
		@RequestParam(required = false) Long categoryId) {
		return ResponseEntity.ok(fundingService.findAllFunding(pageable, postType, categoryId, keyword));
	}

//	@ApiOperation(value = "펀딩 검색 조회", notes = "검색을 통해 제목과 내용에 키워드가 포함된 펀딩을 조회한다.")
//	@GetMapping("/search")
//	public ResponseEntity<Page<FundingListResponse>> findFundingByKeyword(@RequestParam String keyword,
//		@ApiParam(value = "PAGE 번호 (0부터)") @RequestParam(defaultValue = "0") int page,
//		@ApiParam(value = "PAGE 크기") @RequestParam(defaultValue = "12") int size) {
//		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("regDate").descending());
//		return ResponseEntity.ok(fundingService.findFundingByKeyword(keyword, pageRequest));
//	}

	@ApiOperation(value = "해시태그별 펀딩 조회", notes = "해시태그별 펀딩 목록을 조회한다.")
	@GetMapping("/hasgtag")
	private ResponseEntity<Page<FundingListResponse>> findFundingByHashtag(@RequestParam String hashtag,
		@ApiParam(value = "PAGE 번호 (0부터)") @RequestParam(defaultValue = "0") int page,
		@ApiParam(value = "PAGE 크기") @RequestParam(defaultValue = "12") int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("regDate").descending());
		return ResponseEntity.ok(fundingService.findFundingByHashtag(hashtag, pageRequest));
	}

//	@ApiOperation(value = "카테고리별 펀딩 리스트 조회", notes = "카테고리별 펀딩 리스트를 조회한다.")
//	@GetMapping("/category/{categoryId}")
//	public ResponseEntity<Page<FundingListResponse>> findFundingByCategory(@PathVariable Long categoryId,
//		@ApiParam(value = "PAGE 번호 (0부터)") @RequestParam(defaultValue = "0") int page,
//		@ApiParam(value = "PAGE 크기") @RequestParam(defaultValue = "12") int size) {
//		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("regDate").descending());
//		return ResponseEntity.ok(fundingService.findFundingByCategory(categoryId, pageRequest));
//	}

	@ApiOperation(value = "펀딩 생성 시 s3에 파일업로드", notes = "펀딩 생성 시 s3에 파일을 업로드 한다.")
	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
		String fileName = awsS3Uploader.upload(multipartFile, "FundingDetailFiles");
		return fileName;
	}

	@ApiOperation(value = "펀딩 생성 시 썸네일 s3에 파일업로드", notes = "펀딩 생성 시 s3에 썸네일 파일을 업로드 한다.")
	@PostMapping("/upload/thumbnail")
	public String uploadThumbnail(@RequestParam("file") MultipartFile multipartFile) throws IOException {
		String fileName = awsS3Uploader.upload(multipartFile, "thumbnails/");
		return fileName;
	}

	@ApiOperation(value = "펀딩 생성", notes = "새로운 펀딩 게시글을 생성한다.")
	@PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public  ResponseEntity<?> createFunding(@RequestPart FundingRequest data) throws
		IOException,
		NotAuthenticatedTeamException {
		FundingDetailResponse funding = fundingService.createFunding(data);
		return ResponseEntity.ok(funding);
	}

	@ApiOperation(value = "펀딩 상세 조회", notes = "펀딩 게시글 상세를 조회한다.")
	@GetMapping("/{fundingId}")
	public ResponseEntity<FundingDetailResponse> readFundingDetail(@PathVariable Long fundingId,
		@PageableDefault(size = 6, sort = "id", direction = Sort.Direction.DESC)Pageable pageable,
		HttpServletRequest request,
		HttpServletResponse response) throws
		NotAuthenticatedMemberException {
		fundingService.updateHit(fundingId, request, response);
		return ResponseEntity.ok(fundingService.findFundingById(fundingId, pageable));
	}

	@ApiOperation(value = "펀딩 게시글 수정", notes = "펀딩 게시글을 수정한다.")
	@PutMapping(value = "/{fundingId}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<FundingDetailResponse> updateFunding(@PathVariable Long fundingId, @RequestPart FundingRequest data) throws Exception {
		return ResponseEntity.ok(fundingService.updateFunding(fundingId, data));
	}

	@ApiOperation(value = "펀딩 게시글 삭제", notes = "펀딩 게시글을 삭제한다.")
	@DeleteMapping("/{fundingId}")
	public ResponseEntity<?> deleteFunding(@PathVariable Long fundingId) throws FundingNotFoundException {
		fundingService.deleteFunding(fundingId);
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "펀딩 게시글 보고서 작성", notes = "펀딩 게시글 보고서를 작성한다.")
	@PostMapping(value = "/{fundingId}/report", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<FundingReportResponse> createFundingReport(@PathVariable Long fundingId, FundingReportRequest data, BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			log.warn(bindingResult.toString());
		}
		log.info(data.toString());
		FundingReportResponse fundingReport = fundingService.createFundingReport(fundingId, data);
		return ResponseEntity.ok(fundingReport);
	}

	@ApiOperation(value = "펀딩 게시글 보고서 탭 조회", notes = "펀딩 게시글 보고서 탭을 조회한다.")
	@GetMapping("/{fundingId}/report")
	public ResponseEntity<FundingReportResponse> readFundingReport(@PathVariable Long fundingId) {
		return ResponseEntity.ok(fundingService.findFundingReportById(fundingId));
	}

	@ApiOperation(value = "펀딩 게시글 보고서 수정", notes = "펀딩 게시글 보고서를 수정한다.")
	@PutMapping(value = "/{fundingId}/report", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<FundingReportResponse> updateFundingReport(@PathVariable Long fundingId, FundingReportRequest data, BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			log.warn(bindingResult.toString());
		}
		log.info(data.toString());
		return ResponseEntity.ok(fundingService.updateFundingReport(fundingId, data));
	}

	@ApiOperation(value = "펀딩 참여", notes = "회원이 펀딩에 참여한다.")
	@PostMapping("/{fundingId}/pay")
	public ResponseEntity<?> takeFunding(@PathVariable Long fundingId, @RequestBody TakeFundingRequest data) {
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
	public ResponseEntity<?> deleteFundingComment(@PathVariable Long commentId) throws
		CommentNotFoundException,
		NotAuthenticatedMemberException {
		fundingService.deleteFundingComment(commentId);
		return ResponseEntity.ok("삭제 완료");
	}

}
