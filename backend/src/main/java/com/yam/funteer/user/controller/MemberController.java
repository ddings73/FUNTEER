package com.yam.funteer.user.controller;

import com.yam.funteer.user.dto.request.*;
import com.yam.funteer.user.dto.request.member.*;
import com.yam.funteer.user.dto.response.member.MemberAccountResponse;
import com.yam.funteer.user.dto.response.member.MemberProfileResponse;
import com.yam.funteer.user.service.MemberService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import springfox.documentation.annotations.ApiIgnore;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor @Slf4j
@Api(tags ={"일반회원"})
public class MemberController {
	private final MemberService memberService;

	@ApiOperation(value = "회원 가입", notes = "<strong>이메일, 패스워드, 이름, 닉네임, 전화번호</strong>은 필수입력 값이다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "잘못된 요청정보"),
			@ApiResponse(code = 409, message = "중복된 이메일"),
			@ApiResponse(code = 500, message = "서버 에러")
	})
	@PostMapping
	public ResponseEntity signUpMember(@Validated @RequestBody CreateMemberRequest createMemberRequest, BindingResult bindingResult){
		validateBinding(bindingResult);

		log.info("회원가입 시작 =>");
		memberService.createAccountWithOutProfile(createMemberRequest);
		return ResponseEntity.ok().build();
	}


	@ApiOperation(value = "회원 탈퇴", notes = "<strong>비밀번호</strong>를 이용하여 검증한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "잘못된 요청정보"),
			@ApiResponse(code = 500, message = "서버 에러")
	})
	@DeleteMapping
	public ResponseEntity signOutMember(@RequestBody BaseUserRequest baseUserRequest){
		memberService.setAccountSignOut(baseUserRequest);
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "개인회원 프로필 조회", notes = "ID를 이용하여 프로필을 조회할 수 있다")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "잘못된 요청정보"),
			@ApiResponse(code = 500, message = "서버 에러")
	})
	@GetMapping("/{userId}/profile")
	public ResponseEntity<MemberProfileResponse> getMemberProfile(@PathVariable Long userId) {
		MemberProfileResponse memberProfile = memberService.getProfile(userId);
		return ResponseEntity.ok(memberProfile);
	}


	@ApiOperation(value = "개인회원 프로필 수정", notes = "개인회원의 닉네임, 프로필이미지를 수정할 수 있다")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "잘못된 요청정보"),
			@ApiResponse(code = 401, message = "사용자 인증실패"),
			@ApiResponse(code = 500, message = "서버 에러")
	})
	@PutMapping("/profile")
	public void modifyProfile(@Validated @ModelAttribute UpdateMemberProfileRequest request, BindingResult bindingResult){
		validateBinding(bindingResult);
		memberService.updateProfile(request);
	}

	@ApiOperation(value = "회원정보 조회", notes = "회원의 개인정보( 이메일, 이름, 전화번호 )를 조회합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 400, message = "잘못된 요청정보"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	@GetMapping("/{userId}/account")
	public ResponseEntity<MemberAccountResponse> getInfo(@PathVariable Long userId){
		MemberAccountResponse account = memberService.getAccountInfo(userId);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(account);
	}


	@ApiOperation(value = "회원정보 수정", notes = "회원의 비밀번호를 수정합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 400, message = "잘못된 요청정보"),
		@ApiResponse(code = 401, message = "사용자 인증실패"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	@PutMapping("/account")
	public void modifyAccount(@Validated @RequestBody BaseUserRequest baseUserRequest, BindingResult bindingResult) {
		validateBinding(bindingResult);
		memberService.updateAccount(baseUserRequest);
	}

	/**
	 * TODO 미구현
	 */
	@ApiOperation(value = "마일리지 조회", notes = "주어진 회원의 마알리지 정보를 조회할 수 있다")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 400, message = "잘못된 요청정보"),
		@ApiResponse(code = 401, message = "사용자 인증실패"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	@GetMapping("/mileage")
	public ResponseEntity getMileage(@Validated @RequestBody BaseUserRequest baseUserRequest, BindingResult bindingResult){
		validateBinding(bindingResult);
		return ResponseEntity.ok().build();
	}


	/**
	 * TODO 어느정도 구현
	 */
	@ApiOperation(value = "마일리지 충전", notes = "현재 회원의 마알리지를 충전할 수 있다")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 400, message = "잘못된 요청정보"),
		@ApiResponse(code = 401, message = "사용자 인증실패"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	@PostMapping("/charge")
	public ResponseEntity chargeMileage(@Validated @RequestBody ChargeRequest chargeRequest, BindingResult bindingResult){
		validateBinding(bindingResult);
		memberService.chargeMileage(chargeRequest);
		return ResponseEntity.ok().build();
	}


	@ApiOperation(value = "팀 팔로우", notes = "그룹회원번호와 개인회원번호를 이용하여 단체에 대한 팔로우를 진행합니다")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 400, message = "잘못된 요청정보"),
		@ApiResponse(code = 401, message = "사용자 인증실패"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	@PutMapping("/follow")
	public ResponseEntity followTeam(@Validated @RequestBody FollowRequest followRequest, BindingResult bindingResult){
		validateBinding(bindingResult);
		memberService.followTeam(followRequest);
		return ResponseEntity.ok().build();
	}


	@ApiOperation(value = "펀딩 게시글 찜하기", notes = "펀딩번호와 개인회원번호를 받아서 게시글에 대한 찜을 진행")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 400, message = "잘못된 요청정보"),
		@ApiResponse(code = 401, message = "사용자 인증실패"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	@PutMapping("/like")
	public ResponseEntity wishFunding(@Validated @RequestBody WishRequest wishRequest, BindingResult bindingResult){
		validateBinding(bindingResult);
		memberService.wishFunding(wishRequest);
		return ResponseEntity.ok().build();
	}

	@ApiIgnore
	public void validateBinding(BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			fieldErrors.forEach(fieldError -> log.info(fieldError.getDefaultMessage()));
			throw new IllegalArgumentException();
		}
	}
}
