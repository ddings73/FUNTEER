package com.yam.funteer.user.controller;

import com.yam.funteer.user.dto.response.LoginResponse;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        @ApiResponse(code = 400, message = "잘못된 요청 값"),
        @ApiResponse(code = 401, message = "사용자 인증 실패"),
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
    @DeleteMapping("logout")
    public ResponseEntity logout(){
        return ResponseEntity.ok().build();
    }
}
