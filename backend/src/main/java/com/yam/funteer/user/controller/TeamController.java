package com.yam.funteer.user.controller;

import java.util.List;

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.user.dto.request.BaseUserRequest;
import com.yam.funteer.user.dto.request.CreateMemberRequest;
import com.yam.funteer.user.dto.request.CreateTeamRequest;
import com.yam.funteer.user.service.TeamService;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @Slf4j
@RequestMapping("/team")
@RequiredArgsConstructor
@Api(tags = {"단체 회원"})
public class TeamController {
    private final TeamService teamService;
    @ApiOperation(value = "회원 가입", notes = "<strong>이메일, 패스워드, 이름, 닉네임, 전화번호, 인증파일</strong>은 필수입력 값이다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 400, message = "잘못된 요청정보"),
        @ApiResponse(code = 409, message = "중복된 이메일"),
        @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping
    public ResponseEntity signUpMember(@Validated @RequestBody CreateTeamRequest createTeamRequest, BindingResult bindingResult){
        validateBinding(bindingResult);

        log.info("단체 회원가입 시작 =>");
        teamService.createAccountWithOutProfile(createTeamRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<? extends BaseResponseBody> signoutTeam(@Validated @RequestBody BaseUserRequest baseUserRequest
        , BindingResult bindingResult){
        validateBinding(bindingResult);

        teamService.setAccountSignOut(baseUserRequest);
        return ResponseEntity.ok(BaseResponseBody.of("회원탈퇴에 성공하였습니다."));
    }

    public void validateBinding(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(fieldError -> log.info(fieldError.getDefaultMessage()));
            throw new IllegalArgumentException();
        }
    }
}
