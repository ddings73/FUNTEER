package com.yam.funteer.qna.controller;

import java.util.List;

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
import com.yam.funteer.post.entity.Post;
import com.yam.funteer.qna.entity.Qna;
import com.yam.funteer.qna.request.QnaTeamRegisterReq;
import com.yam.funteer.qna.request.QnaMemberRegisterReq;
import com.yam.funteer.qna.response.QnaGetDetailRes;
import com.yam.funteer.qna.response.QnaGetListRes;
import com.yam.funteer.qna.response.QnaRegisterRes;
import com.yam.funteer.qna.service.QnaService;
import com.yam.funteer.user.UserType;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("qna")
public class QnaController {
	private final QnaService qnaService;

	@GetMapping("/team")
	public ResponseEntity<? extends BaseResponseBody>teamQnaGetList(){
		List<Qna>qnaList=qnaService.qnaGetList(UserType.TEAM);
		return ResponseEntity.status(200).body(QnaGetListRes.of("Success"));
	}
	//
	@GetMapping("/member")
	public ResponseEntity<? extends BaseResponseBody>memberQnaGetList(){
		List<Qna>qnaList=qnaService.qnaGetList(UserType.NORMAL);
		return ResponseEntity.status(200).body(QnaGetListRes.of("Success"));
	}
	//
	@PostMapping("/team")
	public ResponseEntity<? extends BaseResponseBody>teamQnaRegister(@RequestBody QnaTeamRegisterReq qnaRegisterReq){
		Qna qnaRegister=qnaService.qnaTeamRegister(qnaRegisterReq);
		return ResponseEntity.status(200).body(QnaRegisterRes.of("Success"));
	}

	@PostMapping("/member")
	public ResponseEntity<? extends BaseResponseBody>memberQnaRegister(@RequestBody QnaMemberRegisterReq qnaRegisterReq){
		Post qnaRegister=qnaService.qnaMemberRegister(qnaRegisterReq);
		return ResponseEntity.status(200).body(QnaRegisterRes.of("Success"));
	}
	//
	@GetMapping("/team/{postId}")
	public ResponseEntity<? extends BaseResponseBody>teamQnaGetDetail(@PathVariable Long postId,@RequestParam String password){
		Qna qnaDetail=qnaService.qnaGetDetail(postId,password);
		return ResponseEntity.status(200).body(QnaGetDetailRes.of("Success"));
	}
	//
	@GetMapping("/member/{postId}")
	public ResponseEntity<? extends BaseResponseBody>memberQnaGetDetail(@PathVariable Long postId,@RequestParam String password){
		Qna qnaDetail=qnaService.qnaGetDetail(postId,password);
		return ResponseEntity.status(200).body(QnaGetDetailRes.of("Success"));
	}
	//
	@PutMapping("/team/{postId}")
	public ResponseEntity<? extends BaseResponseBody>teamQnaModify(@PathVariable Long postId, @RequestBody QnaTeamRegisterReq qnaTeamRegisterReq){
		if(qnaService.qnaTeamModify(postId,qnaTeamRegisterReq)) return ResponseEntity.status(200).body(QnaRegisterRes.of("Success"));
		else return ResponseEntity.status(404).body(QnaRegisterRes.of("Not found"));
	}
	//
	@PutMapping("/member/{postId}")
	public ResponseEntity<? extends BaseResponseBody>memberQnaModify(@PathVariable Long postId,@RequestBody QnaMemberRegisterReq qnaMemberRegisterReq){
		if(qnaService.qnaMemberModify(postId,qnaMemberRegisterReq)) return ResponseEntity.status(200).body(QnaRegisterRes.of("Success"));
		else return ResponseEntity.status(404).body(QnaRegisterRes.of("Not found"));
	}

	@DeleteMapping("/member/{postId}")
	public ResponseEntity<? extends BaseResponseBody>teamQnaDelete(@PathVariable Long postId,@RequestParam Long userId){
		if(qnaService.qnaDelete(postId,userId)) return ResponseEntity.status(200).body(QnaRegisterRes.of("Success"));
		else  return ResponseEntity.status(404).body(QnaRegisterRes.of("Not Found"));
	}

	@DeleteMapping("/member/{postId}")
	public ResponseEntity<? extends BaseResponseBody>memberQnaDelete(@PathVariable Long postId,@RequestParam Long userId){
		if(qnaService.qnaDelete(postId,userId)) return ResponseEntity.status(200).body(QnaRegisterRes.of("Success"));
		else  return ResponseEntity.status(404).body(QnaRegisterRes.of("Not Found"));
	}

}
