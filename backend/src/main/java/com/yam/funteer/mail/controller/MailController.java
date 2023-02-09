package com.yam.funteer.mail.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
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
	@PostMapping("/mail/send")
	public ResponseEntity<Map<String, String>> emailSend(@RequestParam String email) throws Exception {
		String confirmCode = emailService.sendSimpleMessage(email);
		return ResponseEntity.ok(Map.of("confirmCode", confirmCode));
	}
}
