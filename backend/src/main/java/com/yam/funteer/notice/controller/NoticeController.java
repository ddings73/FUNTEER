package com.yam.funteer.notice.controller;

import java.io.IOException;

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
import com.yam.funteer.faq.dto.request.FaqRegisterReq;
import com.yam.funteer.faq.service.FaqService;
import com.yam.funteer.notice.dto.request.NoticeRegistReq;
import com.yam.funteer.notice.service.NoticeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(value = "Notice",tags = "Notice")
@RequiredArgsConstructor
@RestController
@RequestMapping("/notice")
public class NoticeController {
	private final NoticeService noticeService;

	@ApiOperation(value = "notice List")
	@GetMapping("")
	public ResponseEntity<?>NoticeGetList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
		return ResponseEntity.ok(noticeService.noticeGetList(page,size));
	}

	@ApiOperation(value = "notice 등록")
	@PostMapping("")
	public ResponseEntity<?>NoticeRegister( NoticeRegistReq noticeRegistReq){
		return ResponseEntity.ok(noticeService.noticeRegister(noticeRegistReq));
	}


	@ApiOperation(value="notice 상세")
	@GetMapping("/{postId}")
	public ResponseEntity<?>NoticeDetail(@PathVariable Long postId){
		return ResponseEntity.ok(noticeService.noticeGetDetail(postId));
	}

	@ApiOperation(value = "notice 수정")
	@PutMapping("/{postId}")
	public ResponseEntity<?>NoticeModify(@PathVariable Long postId, NoticeRegistReq noticeRegistReq) {
		return ResponseEntity.ok(noticeService.noticeModify(postId,noticeRegistReq));
	}

	@ApiOperation(value = "notice 삭제")
	@DeleteMapping("/{postId}")
	public ResponseEntity<? extends BaseResponseBody>NoticeDelete(@PathVariable Long postId){
		noticeService.noticeDelete(postId);
		return ResponseEntity.ok(BaseResponseBody.of("Delete Success"));
	}

}
