package com.yam.funteer.user.controller;

import com.yam.funteer.user.dto.request.TokenRequest;
import com.yam.funteer.user.dto.response.LoginResponse;
import com.yam.funteer.user.dto.response.TokenInfo;
import com.yam.funteer.user.service.LoginService;
import com.yam.funteer.user.dto.request.LoginRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags ={"로그인"})
public class LoginController {
    private final LoginService loginService;

    @ApiOperation(value = "로그인", notes = "<strong>이메일, 패스워드</strong>는 필수입력 값입니다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 400, message = "잘못된 요청 값, 아이디 혹은 비밀번호가 다르거나 데이터가 다 오지않음"),
        @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Validated @RequestBody LoginRequest loginRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(fieldError -> log.info(fieldError.getDefaultMessage()));
            return ResponseEntity.badRequest().build();
        }
        LoginResponse loginResponse = loginService.processLogin(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }


    @ApiOperation(value = "로그아웃", notes = "토큰을 날려줄꺼에요")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 500, message = "서버 에러")
    })
    @DeleteMapping("/out")
    public ResponseEntity logout(){
        loginService.processLogOut();
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "엑세스토큰 재발급 요청")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 요청 값, 아이디 혹은 비밀번호가 다르거나 데이터가 다 오지않음"),
            @ApiResponse(code = 401, message = "사용자 인증 실패"),
            @ApiResponse(code = 403, message = "엑세스 토큰 만료되지않음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/refresh")
    public ResponseEntity<TokenInfo> regenToken(@RequestHeader String authorization, @RequestBody TokenRequest tokenRequest){
        tokenRequest.setAccessToken(authorization);
        TokenInfo TokenInfo = loginService.regenerateToken(tokenRequest);
        return ResponseEntity.ok(TokenInfo);
    }


    @ApiOperation(value = "카카오로그인")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 400, message = "잘못된 요청 값, 아이디 혹은 비밀번호가 다르거나 데이터가 다 오지않음"),
        @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/login/kakao")
    public ResponseEntity<LoginResponse> kakaoLogin(@RequestBody String email){
        LoginRequest loginRequest = new LoginRequest(email, "kakaoPassword");
        LoginResponse loginResponse = loginService.processKakaoLogin(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}
