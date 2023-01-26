package com.yam.funteer.user.controller;

import java.util.List;

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.user.dto.request.BaseUserRequest;
import com.yam.funteer.user.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @Slf4j
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @DeleteMapping
    public ResponseEntity<? extends BaseResponseBody> signoutTeam(@Validated @RequestBody BaseUserRequest baseUserRequest
        , BindingResult bindingResult){
        validateBinding(bindingResult);

        teamService.signoutTeam(baseUserRequest);
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
