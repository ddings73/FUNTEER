package com.yam.funteer.user.controller;

import com.yam.funteer.common.security.JwtProvider;
import com.yam.funteer.user.dto.request.*;
import com.yam.funteer.user.dto.response.MemberAccountResponse;
import com.yam.funteer.user.dto.response.MemberProfileResponse;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor @Slf4j
@Api(tags ={"일반회원"})
public class MemberController {

	private final JwtProvider jwtProvider;
	private final MemberService memberService;

	@ApiOperation(value = "회원 가입", notes = "<strong>이메일, 패스워드, 이름, 닉네임, 전화번호</strong>은 필수입력 값이다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "잘못된 요청정보"),
			@ApiResponse(code = 409, message = "중복된 이메일"),
			@ApiResponse(code = 500, message = "서버 에러")
	})
	@PostMapping
	public ResponseEntity signUpMember(@Validated @RequestBody CreateAccountRequest createAccountRequest, BindingResult bindingResult){
		validateBinding(bindingResult);

		log.info("회원가입 시작 =>");
		memberService.createAccountWithOutProfile(createAccountRequest);
		return ResponseEntity.ok().build();
	}


	@ApiOperation(value = "회원 탈퇴", notes = "<strong>비밀번호</strong>를 이용하여 검증한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "잘못된 요청정보"),
			@ApiResponse(code = 500, message = "서버 에러")
	})
	@DeleteMapping
	public ResponseEntity signOutMember(@Validated @RequestBody BaseUserRequest baseUserRequest, BindingResult bindingResult){
		validateBinding(bindingResult);

		log.info("회원탈퇴 시작 => {}", baseUserRequest);
		memberService.setAccountSignOut(baseUserRequest);

		return ResponseEntity.ok().build();
	}


	@ApiOperation(value = "회원정보 조회", notes = "회원의 개인정보( 이메일, 이름, 전화번호 )를 조회합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 400, message = "잘못된 요청정보"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	@GetMapping("/{userId}/account")
	public ResponseEntity<MemberAccountResponse> getInfo(@PathVariable Long userId, @RequestHeader String authorization){
		if(jwtProvider.verifyById(userId, authorization)) {
			MemberAccountResponse response = memberService.getAccount(userId);
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}


	/**
	 * TODO 파일업로드 필요
	 */
	@ApiOperation(value = "회원정보 수정", notes = "회원의 비밀번호를 수정합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 400, message = "잘못된 요청정보"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	@PostMapping("/account")
	public void modifyAccount(@Validated @RequestBody UpdateAccountRequest updateAccountRequest, BindingResult bindingResult) {
		validateBinding(bindingResult);
		memberService.updateAccount(updateAccountRequest);
	}


	@ApiOperation(value = "개인회원 프로필 조회", notes = "ID를 이용하여 프로필을 조회할 수 있다")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 400, message = "잘못된 요청정보"),
		@ApiResponse(code = 401, message = "사용자 인증실패"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	@GetMapping("/{userId}/profile")
	public ResponseEntity<MemberProfileResponse> getMemberProfile(@PathVariable Long userId) {
		MemberProfileResponse memberProfile = memberService.getProfile(userId);
		return ResponseEntity.ok(memberProfile);
	}


	/**
	 * TODO 파일업로드 완료 / 수정확인 필요
	 */
	@ApiOperation(value = "개인회원 프로필 수정", notes = "개인회원의 닉네임, 프로필이미지를 수정할 수 있다")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 400, message = "잘못된 요청정보"),
		@ApiResponse(code = 401, message = "사용자 인증실패"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	@PostMapping("/profile")
	public void modifyProfile(@Validated @ModelAttribute UpdateProfileRequest updateProfileRequest, BindingResult bindingResult){
		validateBinding(bindingResult);
		memberService.updateProfile(updateProfileRequest);
	}


	/**
	 * TODO 미구현
	 */
	@ApiOperation(value = "마일리지 조회", notes = "현재 회원의 마알리지 정보를 조회할 수 있다")
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
	 * TODO 미구현
	 */
	@ApiOperation(value = "마일리지 충전", notes = "현재 회원의 마알리지를 충전할 수 있다")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 400, message = "잘못된 요청정보"),
		@ApiResponse(code = 401, message = "사용자 인증실패"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	@GetMapping("/charge")
	public ResponseEntity getMileage(@Validated @RequestBody ChargeRequest chargeRequest, BindingResult bindingResult){
		validateBinding(bindingResult);
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
	public ResponseEntity followTeam(@RequestBody Long teamId, @RequestBody Long memberId){
		memberService.followTeam(teamId, memberId);
		return ResponseEntity.ok().build();
	}


	@ApiOperation(value = "펀딩 게시글 찜하기", notes = "펀딩번호와 개인회원번호를 받아서 게시글에 대한 찜을 진행")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 400, message = "잘못된 요청정보"),
		@ApiResponse(code = 401, message = "사용자 인증실패"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	@PutMapping("/like/{fundingId}")
	public ResponseEntity wishFunding(@PathVariable("fundingId") Long fundingId, @RequestBody Long memberId){
		memberService.wishFunding(fundingId, memberId);
		return ResponseEntity.ok().build();
	}


	/**
	 * TODO 전화번호 인증 필요
	 */
	@ApiOperation(value = "이메일 찾기", notes = "전화번호 인증을 통해서 회원 이메일을 돌려받을 수 있다")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 400, message = "잘못된 요청정보"),
		@ApiResponse(code = 401, message = "사용자 인증실패"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	@GetMapping("/forget/email") // 대기
	public ResponseEntity<Map<String, String>> forgetEmail(){
		HashMap<String, String> map = new HashMap<>();
		map.put("email", "kim@ssafy.com");
		return ResponseEntity.ok(map);
	}


	/**
	 * TODO 이메일 인증 필요
	 */
	@ApiOperation(value = "비밀번호 찾기", notes = "이메일 인증을 통해서 임시 비밀번호를 받을 수 있다")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 400, message = "잘못된 요청정보"),
		@ApiResponse(code = 401, message = "사용자 인증실패"),
		@ApiResponse(code = 500, message = "서버 에러"),
	})
	@GetMapping("/forget/pw") // 대기
	public ResponseEntity<Map<String, String>> forgetPassword(){
		HashMap<String, String> map = new HashMap<>();
		map.put("tmpPassword", "Q123qwe23@@!");
		return ResponseEntity.ok(map);
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
