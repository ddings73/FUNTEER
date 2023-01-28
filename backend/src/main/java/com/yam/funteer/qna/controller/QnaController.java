package com.yam.funteer.qna.controller;

import javax.transaction.Transactional;

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
import com.yam.funteer.common.code.UserType;
import com.yam.funteer.qna.entity.Qna;
import com.yam.funteer.qna.exception.QnaNotFoundException;
import com.yam.funteer.qna.request.QnaRegisterReq;
import com.yam.funteer.qna.request.QnaReplyReq;
import com.yam.funteer.qna.service.QnaService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Transactional
@RequestMapping("qna")
@Api(value="QnA",tags="QNA")
public class QnaController {
	private final QnaService qnaService;

	@ApiOperation(value="팀회원 QnA 리스트")
	@GetMapping("/team")
	public ResponseEntity<? extends BaseResponseBody>teamQnaGetList(){
		qnaService.qnaGetList(UserType.TEAM);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

	@ApiOperation(value="개인회원 QnA 리스트")
	@GetMapping("/member")
	public ResponseEntity<? extends BaseResponseBody>memberQnaGetList(){
		qnaService.qnaGetList(UserType.NORMAL);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

	@ApiOperation(value="팀회원 QnA 등록")
	@PostMapping("/team")
	public ResponseEntity<? extends BaseResponseBody>teamQnaRegister(@RequestBody QnaRegisterReq qnaRegisterReq){
		qnaService.qnaRegister(qnaRegisterReq);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

	@ApiOperation(value="개인회원 QnA 등록")
	@PostMapping("/member")
	public ResponseEntity<? extends BaseResponseBody>memberQnaRegister(@RequestBody QnaRegisterReq qnaRegisterReq){
		qnaService.qnaRegister(qnaRegisterReq);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

	@ApiOperation(value="팀회원 QnA 상세")
	@GetMapping("/team/{postId}")
	public ResponseEntity<? extends BaseResponseBody>teamQnaGetDetail(@PathVariable Long postId,@RequestParam Long userId) throws
		QnaNotFoundException {
		qnaService.qnaGetDetail(postId,userId);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

	@ApiOperation(value="개인회원 QnA 상세")
	@GetMapping("/member/{postId}")
	public ResponseEntity<? extends BaseResponseBody>memberQnaGetDetail(@PathVariable Long postId,@RequestParam Long userId) throws
		QnaNotFoundException {
		qnaService.qnaGetDetail(postId,userId);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

	@ApiOperation(value="팀회원 QnA 수정")
	@PutMapping("/team/{postId}")
	public ResponseEntity<? extends BaseResponseBody>teamQnaModify(@PathVariable Long postId, @RequestBody QnaRegisterReq qnaModifyReq) throws
		QnaNotFoundException {
		qnaService.qnaModify(postId,qnaModifyReq);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

	@ApiOperation(value="개인회원 QnA 수정")
	@PutMapping("/member/{postId}")
	public ResponseEntity<? extends BaseResponseBody>memberQnaModify(@PathVariable Long postId,@RequestBody QnaRegisterReq qnaModifyReq) throws
		QnaNotFoundException {
		qnaService.qnaModify(postId,qnaModifyReq);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

	@ApiOperation(value="팀회원 QnA 삭제")
	@DeleteMapping("/team/{postId}")
	public ResponseEntity<? extends BaseResponseBody>teamQnaDelete(@PathVariable Long postId,@RequestParam Long userId) throws
		QnaNotFoundException {
		qnaService.qnaDelete(postId,userId);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

	@ApiOperation(value="개인회원 QnA 삭제")
	@DeleteMapping("/member/{postId}")
	public ResponseEntity<? extends BaseResponseBody>memberQnaDelete(@PathVariable Long postId,@RequestParam Long userId) throws
		QnaNotFoundException {
		qnaService.qnaDelete(postId,userId);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

}
