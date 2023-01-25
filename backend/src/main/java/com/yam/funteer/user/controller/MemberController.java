package com.yam.funteer.user.controller;

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.user.UserType;
import com.yam.funteer.user.dto.response.MemberProfileResponse;
import com.yam.funteer.user.dto.request.BaseUserRequest;
import com.yam.funteer.user.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.yam.funteer.user.dto.request.CreateMemberRequest;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor @Slf4j
@Api(value = "일반회원 API", tags ={"Member"})
public class MemberController {

	private final MemberService memberService;

	@ApiOperation(value = "회원 가입", notes = "<strong>이메일, 패스워드, 이름, 닉네임, 전화번호</strong>은 필수입력 값이다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "잘못된 요청 값"),
			@ApiResponse(code = 409, message = "중복된 이메일"),
			@ApiResponse(code = 500, message = "서버 에러")
	})
	@PostMapping
	public ResponseEntity<? extends BaseResponseBody> signupMember(@Validated @RequestBody CreateMemberRequest createMemberRequest, BindingResult bindingResult){
		validateBinding(bindingResult);

		log.info("회원가입 시작");
		memberService.signupMember(createMemberRequest);
		return ResponseEntity.ok(BaseResponseBody.of("회원가입이 완료되었습니다."));
	}
	@ApiOperation(value = "회원 탈퇴", notes = "<strong>비밀번호</strong>를 이용하여 검증한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "잘못된 요청 값"),
			@ApiResponse(code = 500, message = "서버 에러")
	})
	@DeleteMapping
	public ResponseEntity<? extends BaseResponseBody> signoutMember(@Validated @RequestBody BaseUserRequest baseUserRequest, BindingResult bindingResult){
		validateBinding(bindingResult);

		log.info("회원탈퇴 시작=> {}", baseUserRequest);
		memberService.signoutMember(baseUserRequest);

		return ResponseEntity.ok(BaseResponseBody.of("회원 탈퇴가 완료되었습니다."));
	}


	@Secured(UserType.ROLES.USER)
	@GetMapping("/account")
	public ResponseEntity<? extends BaseResponseBody> getMemberInfo(@Validated @RequestBody BaseUserRequest baseUserRequest, BindingResult bindingResult){
		validateBinding(bindingResult);

		log.info("회원정보 조회 시작");

		return null;
	}

	@Secured(UserType.ROLES.USER)
	@PutMapping("/account")
	public ResponseEntity<? extends BaseResponseBody> modifyAccount(@Validated @RequestBody BaseUserRequest baseUserRequest, BindingResult bindingResult){
		validateBinding(bindingResult);

		return null;
	}

	@GetMapping("/profile")
	public ResponseEntity<? extends BaseResponseBody> getMemberProfile(@Validated @RequestBody BaseUserRequest baseUserRequest, BindingResult bindingResult){
		validateBinding(bindingResult);

		log.info("회원 프로필 조회 시작");
		MemberProfileResponse memberProfile = memberService.getMemberProfile(baseUserRequest);
		return ResponseEntity.ok(memberProfile);
	}

	@PutMapping("/profile")
	public ResponseEntity<? extends BaseResponseBody> modifyProfile(){
		return null;
	}

	public void validateBinding(BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			fieldErrors.forEach(fieldError -> log.info(fieldError.getDefaultMessage()));
			throw new IllegalArgumentException();
		}
	}
}
