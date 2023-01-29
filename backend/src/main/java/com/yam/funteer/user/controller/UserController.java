package com.yam.funteer.user.controller;

import com.yam.funteer.user.dto.request.EmailConfirmRequest;
import com.yam.funteer.user.service.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Api(tags = {"회원 공통"})
public class UserController {
    private final EmailService emailService;

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
    public ResponseEntity forgetPassword(@RequestBody EmailConfirmRequest emailConfirmRequest){

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "이메일 보내기", notes = "입력받은 메일로 인증코드를 보낸다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/mail/send")
    public ResponseEntity<Map<String, String>> emailSend(@RequestParam String email) throws Exception {
        String confirmCode = emailService.sendSimpleMessage(email);
        Map<String, String> map = new HashMap<>();
        map.put("confirmCode", confirmCode);
        return ResponseEntity.ok(map);
    }
}
