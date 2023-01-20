package com.yam.funteer.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yam.funteer.code.GroupCode;
import com.yam.funteer.member.dto.MemberRequestDto;

@RestController
@RequestMapping("/member")
public class MemberController {

	@PostMapping("/")
	public ResponseEntity signupMember(@ModelAttribute MemberRequestDto memberRequestDto){
		return null;
	}
}
