package com.yam.funteer.user.controller;

import java.util.List;

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.user.dto.request.BaseUserRequest;
import com.yam.funteer.user.dto.request.team.CreateTeamRequest;
import com.yam.funteer.user.dto.request.team.UpdateTeamAccountRequest;
import com.yam.funteer.user.dto.request.team.UpdateTeamProfileRequest;
import com.yam.funteer.user.dto.response.team.TeamAccountResponse;
import com.yam.funteer.user.dto.response.team.TeamProfileResponse;
import com.yam.funteer.user.service.TeamService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity signUpTeam(@Validated @ModelAttribute CreateTeamRequest createTeamRequest, BindingResult bindingResult){
        validateBinding(bindingResult);

        log.info("단체 회원가입 시작 =>");
        teamService.createAccountWithOutProfile(createTeamRequest);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "단체회원 탈퇴", notes = "<strong>비밀번호</strong>를 이용하여 검증한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 요청정보"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @DeleteMapping
    public ResponseEntity signOutTeam(@Validated @RequestBody BaseUserRequest baseUserRequest
        , BindingResult bindingResult){
        validateBinding(bindingResult);

        teamService.setAccountSignOut(baseUserRequest);
        return ResponseEntity.ok().build();
    }


    @ApiOperation(value = "단체회원 프로필 조회", notes = "ID를 이용하여 프로필을 조회할 수 있다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 요청정보"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/{userId}/profile")
    public ResponseEntity<TeamProfileResponse> getProfile(@PathVariable Long userId,
                                          @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable){
        TeamProfileResponse teamProfile = teamService.getTeamProfile(userId, pageable);
        return ResponseEntity.ok(teamProfile);
    }

    @ApiOperation(value = "단체회원 프로필 수정", notes = "단체회원의 단체설명, 프로필이미지, 배너를 수정할 수 있다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 요청정보"),
            @ApiResponse(code = 401, message = "사용자 인증실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PutMapping("/profile")
    public void modifyProfile(@Validated @ModelAttribute UpdateTeamProfileRequest request, BindingResult bindingResult){
        validateBinding(bindingResult);
        teamService.updateProfile(request);
    }

    @ApiOperation(value = "단체회원 개인정보 조회", notes = "ID를 이용하여 개인정보 조회할 수 있다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 요청정보"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/account")
    public ResponseEntity<TeamAccountResponse> getAccountInfo(){
        TeamAccountResponse teamAccount = teamService.getTeamAccount();
        return ResponseEntity.ok(teamAccount);
    }

    @ApiOperation(value = "단체회원 개인정보 수정", notes = "비밀번호를 검증하여 ")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 400, message = "잘못된 요청정보"),
        @ApiResponse(code = 401, message = "사용자 인증실패"),
        @ApiResponse(code = 500, message = "서버 에러")
    })
    @PutMapping("/account")
    public void modifyAccount(@Validated @ModelAttribute UpdateTeamAccountRequest request, BindingResult bindingResult){
        teamService.updateAccount(request);
    }

    public void validateBinding(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(fieldError -> log.info(fieldError.getDefaultMessage()));
            throw new IllegalArgumentException();
        }
    }
}
