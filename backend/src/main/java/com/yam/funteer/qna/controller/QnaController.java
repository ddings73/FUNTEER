package com.yam.funteer.qna.controller;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.yam.funteer.user.entity.User;

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

	@ApiOperation(value="팀회원 QnA 리스트")
	@GetMapping("/team")
	public ResponseEntity<?>teamQnaGetList(){
		return ResponseEntity.ok(qnaService.qnaGetList(UserType.TEAM));
	}

	@ApiOperation(value="개인회원 QnA 리스트")
	@GetMapping("/member")
	public ResponseEntity<?>memberQnaGetList(){
		return ResponseEntity.ok(qnaService.qnaGetList(UserType.NORMAL));
	}

	@ApiOperation(value="팀회원 QnA 등록")
	@PostMapping("/team")
	public ResponseEntity<? extends BaseResponseBody>teamQnaRegister(@RequestPart(value ="qnaRegisterReq" ) QnaRegisterReq qnaRegisterReq,@RequestPart(value = "files",required = false)
		List<MultipartFile>files) throws IOException {
		qnaService.qnaRegister(qnaRegisterReq,files);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

	@ApiOperation(value="개인회원 QnA 등록")
	@PostMapping("/member")
	public ResponseEntity<? extends BaseResponseBody> memberQnaRegister(@RequestPart(value ="qnaRegisterReq" ) QnaRegisterReq qnaRegisterReq,@RequestPart(value = "files",required = false)
		List<MultipartFile>files) throws IOException {
		qnaService.qnaRegister(qnaRegisterReq,files);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

	@ApiOperation(value="팀회원 QnA 상세")
	@GetMapping("/team/{postId}")
	public ResponseEntity<?>teamQnaGetDetail(@PathVariable Long postId) throws
		QnaNotFoundException {
		return ResponseEntity.ok(qnaService.qnaGetDetail(postId));
	}

	@ApiOperation(value="개인회원 QnA 상세")
	@GetMapping("/member/{postId}")
	public ResponseEntity<?>memberQnaGetDetail(@PathVariable Long postId) throws
		QnaNotFoundException {
		return ResponseEntity.ok(qnaService.qnaGetDetail(postId));
	}

	@ApiOperation(value="팀회원 QnA 수정")
	@PutMapping("/team/{postId}")
	public ResponseEntity<? extends BaseResponseBody>teamQnaModify(@PathVariable Long postId, @RequestPart(value ="qnaModifyReq" ) QnaRegisterReq qnaModifyReq,@RequestPart
		(value = "files",required = false)List<MultipartFile>files) throws
		QnaNotFoundException, IOException {
		qnaService.qnaModify(postId,qnaModifyReq,files);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

	@ApiOperation(value="개인회원 QnA 수정")
	@PutMapping("/member/{postId}")
	public ResponseEntity<? extends BaseResponseBody>memberQnaModify(@PathVariable Long postId, @RequestPart(value ="qnaModifyReq" ) QnaRegisterReq qnaModifyReq,@RequestPart
		(value = "files",required = false)List<MultipartFile>files) throws
		QnaNotFoundException, IOException {
		qnaService.qnaModify(postId,qnaModifyReq,files);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

	@ApiOperation(value="팀회원 QnA 삭제")
	@DeleteMapping("/team/{postId}")
	public ResponseEntity<? extends BaseResponseBody>teamQnaDelete(@PathVariable Long postId) throws
		QnaNotFoundException {
		qnaService.qnaDelete(postId);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

	@ApiOperation(value="개인회원 QnA 삭제")
	@DeleteMapping("/member/{postId}")
	public ResponseEntity<? extends BaseResponseBody>memberQnaDelete(@PathVariable Long postId) throws
		QnaNotFoundException {
		qnaService.qnaDelete(postId);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

}
