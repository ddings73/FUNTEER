package com.yam.funteer.member.controller;

import com.yam.funteer.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.yam.funteer.member.dto.MemberRequestDto;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/")
	public ResponseEntity signupMember(@ModelAttribute MemberRequestDto memberRequestDto){
		return null;
	}

	@DeleteMapping("/")
	public ResponseEntity signoutMember(){
		return null;
	}

	@GetMapping("/account")
	public ResponseEntity getMemberInfo(){
		return null;
	}

	@PutMapping("/account")
	public ResponseEntity modifyAccount(){
		return null;
	}

	@GetMapping("/pfofile")
	public ResponseEntity getMemberProfile(){
		return null;
	}

	@PutMapping("/profile")
	public ResponseEntity modifyProfile(){
		return null;
	}


}
