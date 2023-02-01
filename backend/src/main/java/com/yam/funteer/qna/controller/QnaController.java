package com.yam.funteer.qna.controller;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.qna.dto.request.QnaRegisterReq;

import com.yam.funteer.qna.service.QnaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Transactional
@RequestMapping("/qna")
@Api(value="QnA",tags="QNA")
public class QnaController {
	private final QnaService qnaService;

	@ApiOperation(value="본인 QnA 리스트")
	@GetMapping("")
	public ResponseEntity<?>qnaGetList(){
		return ResponseEntity.ok(qnaService.qnaGetList());
	}


	@ApiOperation(value="QnA 등록")
	@PostMapping("")
	public ResponseEntity<?>qnaRegister(QnaRegisterReq qnaRegisterReq){
		return ResponseEntity.ok(qnaService.qnaRegister(qnaRegisterReq));
	}


	@ApiOperation(value=" QnA 상세")
	@GetMapping("/{postId}")
	public ResponseEntity<?>qnaGetDetail(@PathVariable Long postId){
		return ResponseEntity.ok(qnaService.qnaGetDetail(postId));
	}


	@ApiOperation(value="QnA 수정")
	@PutMapping("/{postId}")
	public ResponseEntity<?>qnaModify(@PathVariable Long postId, QnaRegisterReq qnaModifyReq){
		return ResponseEntity.ok(qnaService.qnaModify(postId,qnaModifyReq));
	}

	@ApiOperation(value=" QnA 삭제")
	@DeleteMapping("/{postId}")
	public ResponseEntity<? extends BaseResponseBody>qnaDelete(@PathVariable Long postId){
		qnaService.qnaDelete(postId);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}
}
