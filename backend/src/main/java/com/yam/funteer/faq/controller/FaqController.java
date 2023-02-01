package com.yam.funteer.faq.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.faq.dto.request.FaqRegisterReq;
import com.yam.funteer.faq.service.FaqService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(value = "FAQ",tags = "FAQ")
@RequiredArgsConstructor
@RestController
@RequestMapping("/faq")
public class FaqController {
	private final FaqService faqService;

	@ApiOperation(value = "faq List")
	@GetMapping("")
	public ResponseEntity<?>FaqGetList(){
		return ResponseEntity.ok(faqService.faqGetList());
	}

	@ApiOperation(value = "faq 등록")
	@PostMapping("")
	public ResponseEntity<?>FaqRegister(@RequestBody FaqRegisterReq faqRegisterReq){
		return ResponseEntity.ok(faqService.faqRegister(faqRegisterReq));
	}

	@ApiOperation(value = "faq 상세")
	@GetMapping("/{postId}")
	public ResponseEntity<?>FaqGetDetail(@PathVariable Long postId){
		return ResponseEntity.ok(faqService.faqGetDetail(postId));
	}

	@ApiOperation(value = "faq 수정")
	@PutMapping("/{postId}")
	public ResponseEntity<?>FaqModify(@PathVariable Long postId,@RequestBody FaqRegisterReq faqRegisterReq){
		return ResponseEntity.ok(faqService.faqModify(postId,faqRegisterReq));
	}

	@ApiOperation(value = "faq 삭제")
	@DeleteMapping("/{postId}")
	public ResponseEntity<? extends BaseResponseBody>FaqDelete(@PathVariable Long postId){
		faqService.faqDelete(postId);
		return ResponseEntity.ok(BaseResponseBody.of("Delete Success"));
	}

}
