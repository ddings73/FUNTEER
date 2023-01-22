package com.yam.funteer.user.member.controller;

import com.yam.funteer.user.member.dto.MemberResponseDto;
import com.yam.funteer.user.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.yam.funteer.user.member.dto.MemberRequestDto;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor @Slf4j
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/")
	public ResponseEntity<MemberResponseDto> signupMember(@ModelAttribute MemberRequestDto requestDto, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return ResponseEntity.badRequest().build();
		}
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
