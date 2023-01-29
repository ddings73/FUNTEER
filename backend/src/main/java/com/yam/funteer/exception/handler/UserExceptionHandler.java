package com.yam.funteer.exception.handler;

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.exception.EmailDuplicateException;
import com.yam.funteer.exception.UserNotFoundException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice("com.yam.funteer.user")
public class UserExceptionHandler {

    @ExceptionHandler(EmailDuplicateException.class)
    public ResponseEntity<BaseResponseBody> handleEmailDuplicateException(EmailDuplicateException ex){
        log.info("EmailDuplicateException => {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(BaseResponseBody.of("중복 이메일입니다."));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<BaseResponseBody> handleUserNotFoundException(UserNotFoundException ex){
        log.info("UserNotFoundException => {}", ex.getMessage());
        return ResponseEntity.badRequest()
            .body(BaseResponseBody.of("존재하지 않는 회원입니다."));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponseBody> handleIllegalArgumentException(IllegalArgumentException ex){
        log.info("IllegalArgumentException => {}", ex.getMessage());
        return ResponseEntity.badRequest()
            .body(BaseResponseBody.of(ex.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<BaseResponseBody> handleBadCredentialsException(BadCredentialsException ex){
        log.info("BadCredentialsException => {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(BaseResponseBody.of("아이디 혹은 비밀번호가 다릅니다."));
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<BaseResponseBody> handleJwtException(JwtException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(BaseResponseBody.of(ex.getMessage()));
    }
}
