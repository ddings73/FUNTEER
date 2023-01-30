package com.yam.funteer.qna.controller;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.yam.funteer.qna.dto.request.QnaReplyReq;
import com.yam.funteer.qna.exception.QnaNotFoundException;
import com.yam.funteer.qna.service.ReplyService;
import com.yam.funteer.user.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Transactional
@Api(value="QnA-Reply",tags="Reply")
public class ReplyController {

	private final ReplyService replyService;

	@ApiOperation(value="답변 상세글")
	@GetMapping("/reply/{replyId}")
	public ResponseEntity<? extends BaseResponseBody> replyGetDetail(@PathVariable Long replyId) throws
		QnaNotFoundException {
		replyService.replyGetDetail(replyId);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

	@ApiOperation(value="답변 등록")
	@PostMapping("/qna/{qnaId}/reply")
	public ResponseEntity<? extends BaseResponseBody> replyRegister(@PathVariable Long qnaId,@RequestBody QnaReplyReq qnaReplyReq) throws
		QnaNotFoundException {
		replyService.replyRegister(qnaId,qnaReplyReq);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

	@ApiOperation(value="답변 수정")
	@PutMapping("/reply/{replyId}")
	public ResponseEntity<? extends BaseResponseBody> replyModify(@PathVariable Long replyId,@RequestBody QnaReplyReq qnaReplyReq) throws
		QnaNotFoundException {
		replyService.replyModify(replyId,qnaReplyReq);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

	@ApiOperation(value = "답변 삭제")
	@DeleteMapping("/reply/{replyId}")
	public ResponseEntity<? extends BaseResponseBody> replyDelete(@PathVariable Long replyId) {
		replyService.replyDelete(replyId);
		return ResponseEntity.ok(BaseResponseBody.of("Success"));
	}

}
