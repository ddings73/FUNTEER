package com.yam.funteer.user;

import com.yam.funteer.user.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    @GetMapping("/login")
    public ResponseEntity loginUser(@ModelAttribute LoginRequest loginRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(fieldError -> log.info(fieldError.getDefaultMessage()));
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok().build();
    }

}
