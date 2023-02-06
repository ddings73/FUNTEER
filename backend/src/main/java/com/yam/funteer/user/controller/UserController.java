package com.yam.funteer.user.controller;

import com.yam.funteer.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotBlank;

@RestController
@RequiredArgsConstructor
@Api(tags = {"회원 공통"})
public class UserController {

    private final UserService userService;

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
    @PutMapping("/forget/email") // 대기
    public ResponseEntity<Map<String, String>> forgetEmail(){
        HashMap<String, String> map = new HashMap<>();
        map.put("email", "kim@ssafy.com");
        return ResponseEntity.ok(map);
    }


    /**
     * TODO 이메일 인증 필요
     */
    @ApiOperation(value = "비밀번호 찾기", notes = "이메일 인증 이후 입력받은 이메일에 해당하는 회원의 비밀번호를 변경한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 요청정보"),
            @ApiResponse(code = 401, message = "사용자 인증실패"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @PutMapping("/forget/pw") // 대기
    public ResponseEntity forgetPassword(){

        return ResponseEntity.ok().build();
    }


    @ApiOperation(value = "이메일 검증", notes = "이메일이 중복되는지 검사합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 409, message = "이메일 중복 or 잘못된 요청정보"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @GetMapping("/confirm/email")
    public ResponseEntity confirmEmail(@RequestParam @NotBlank String email){
        userService.confirmEmail(email);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "이름 검증", notes = "단체명이 중복되는지 검사합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 409, message = "단체명 중복 or 잘못된 요청정보"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @GetMapping("/confirm/name")
    public ResponseEntity confirmName(@RequestParam @NotBlank String name){
        userService.confirmName(name);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "닉네임 검증", notes = "개인회원의 닉네임이 중복되는지 검사합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 409, message = "닉네임 중복 or 잘못된 요청정보"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @GetMapping("/confirm/nickname")
    public ResponseEntity confirmNickname(@RequestParam @NotBlank String nickname){
        userService.confirmNickname(nickname);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="전화번호 중복검증", notes = "회원의 전화번호가 중복되는지 검사합니다")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 409, message = "전화번호 중복"),
        @ApiResponse(code = 500, message = "서버 에러"),
    })
    @GetMapping("/confirm/phone")
    public ResponseEntity confirmPhoneNumber(@RequestParam @NotBlank String phone){
        userService.confirmPhone(phone);
        return ResponseEntity.ok().build();
    }
}
