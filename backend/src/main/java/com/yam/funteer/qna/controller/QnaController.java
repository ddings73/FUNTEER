package com.yam.funteer.qna.controller;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.common.code.UserType;
import com.yam.funteer.qna.dto.request.QnaRegisterReq;
import com.yam.funteer.qna.exception.QnaNotFoundException;

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
	public ResponseEntity<?>QnaGetList(){
		return ResponseEntity.ok(qnaService.qnaGetList());
	}


	@ApiOperation(value="QnA 등록")
	@PostMapping("")
	public ResponseEntity<?>teamQnaRegister(@RequestPart(value ="qnaRegisterReq" ) QnaRegisterReq qnaRegisterReq,@RequestPart(value = "files",required = false)
		List<MultipartFile>files){
		return ResponseEntity.ok(qnaService.qnaRegister(qnaRegisterReq,files));
	}


	@ApiOperation(value=" QnA 상세")
	@GetMapping("/{postId}")
	public ResponseEntity<?>teamQnaGetDetail(@PathVariable Long postId) throws
		QnaNotFoundException {
		return ResponseEntity.ok(qnaService.qnaGetDetail(postId));
	}


	@ApiOperation(value="QnA 수정")
	@PutMapping("/{postId}")
	public ResponseEntity<?>teamQnaModify(@PathVariable Long postId, @RequestPart(value ="qnaModifyReq" ) QnaRegisterReq qnaModifyReq,@RequestPart
		(value = "files",required = false)List<MultipartFile>files) throws
		QnaNotFoundException {
		return ResponseEntity.ok(qnaService.qnaModify(postId,qnaModifyReq,files));
	}

	@ApiOperation(value=" QnA 삭제")
	@DeleteMapping("/{postId}")
	public ResponseEntity<? extends BaseResponseBody>teamQnaDelete(@PathVariable Long postId) throws
		QnaNotFoundException {
		qnaService.qnaDelete(postId);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}


}
