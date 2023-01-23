package com.yam.funteer.exception.handler;

import com.yam.funteer.exception.EmailDuplicateException;
import com.yam.funteer.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(EmailDuplicateException.class)
    public ResponseEntity<String> handleEmailDuplicateException(EmailDuplicateException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
