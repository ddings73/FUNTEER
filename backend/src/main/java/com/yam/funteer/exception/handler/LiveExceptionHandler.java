package com.yam.funteer.exception.handler;

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.exception.SessionNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice("com.yam.funteer.live")
public class LiveExceptionHandler {

    @ExceptionHandler(SessionNotFoundException.class)
    public ResponseEntity<BaseResponseBody> handleSessionNotFoundException(SessionNotFoundException ex){
        log.warn("SessionNotFoundException => {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(BaseResponseBody.of("개설되지 않은 라이브 세션입니다."));
    }
}
