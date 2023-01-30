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
import org.springframework.web.bind.annotation.RestController;

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.qna.dto.request.QnaReplyReq;
import com.yam.funteer.qna.exception.QnaNotFoundException;
import com.yam.funteer.qna.service.ReplyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Transactional
@Api(value="QnA-Reply",tags="Reply")
@RequestMapping("/qna")
public class ReplyController {

	private final ReplyService replyService;

	@ApiOperation(value="답변 상세글")
	@GetMapping("/{qnaId}/reply")
	public ResponseEntity<?> replyGetDetail(@PathVariable Long qnaId) throws
		QnaNotFoundException {
		return ResponseEntity.ok(replyService.replyGetDetail(qnaId));
	}

	@ApiOperation(value="답변 등록")
	@PostMapping("/{qnaId}/reply")
	public ResponseEntity<?> replyRegister(@PathVariable Long qnaId,@RequestBody QnaReplyReq qnaReplyReq) throws
		QnaNotFoundException {
		return ResponseEntity.ok(replyService.replyRegister(qnaId,qnaReplyReq));
	}

	@ApiOperation(value="답변 수정")
	@PutMapping("/{qnaId}/reply")
	public ResponseEntity<?> replyModify(@PathVariable Long qnaId,@RequestBody QnaReplyReq qnaReplyReq) throws
		QnaNotFoundException {
		return ResponseEntity.ok(replyService.replyModify(qnaId,qnaReplyReq));
	}

	@ApiOperation(value = "답변 삭제")
	@DeleteMapping("/{qnaId}/reply")
	public ResponseEntity<? extends BaseResponseBody> replyDelete(@PathVariable Long qnaId) throws
		QnaNotFoundException {
		replyService.replyDelete(qnaId);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

}
