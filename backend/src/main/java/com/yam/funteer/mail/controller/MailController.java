package com.yam.funteer.mail.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yam.funteer.mail.service.EmailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController @Api(tags = {"이메일"})
@RequiredArgsConstructor
public class MailController {

	private final EmailService emailService;
	@ApiOperation(value = "이메일 보내기", notes = "입력받은 메일로 인증코드를 보낸다")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	@GetMapping("/mail/send")
	public ResponseEntity emailSend(@RequestParam String email) throws Exception {
		emailService.sendSimpleMessage(email);
		return ResponseEntity.ok("인증 메일이 전송되었습니다.");
	}

	@ApiOperation(value = "이메일 인증코드 검증", notes = "입력받은 메일과 인증코드를 검증한다")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "인증 실패")
	})
	@GetMapping("/mail/confirm")
	public ResponseEntity emailCodeConfirm(@RequestParam String email, @RequestParam String code){
		return emailService.confirmCode(email, code) ? ResponseEntity.ok("이메일 인증에 성공하였습니다.") : ResponseEntity.badRequest().body("이메일 인증에 실패하였습니다.");
	}
}
