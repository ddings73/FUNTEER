package com.yam.funteer.faq.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.faq.exception.FaqNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice("com.yam.funteer.faq")
public class FaqExceptionHandler {

    @ExceptionHandler({FaqNotFoundException.class})
    public ResponseEntity<BaseResponseBody> handleFaqExceptionHandler(FaqNotFoundException exception){
        log.info("FaqNotFoundException => {}", exception.getMessage());
        return ResponseEntity.badRequest()
            .body(BaseResponseBody.of("존재하지 않는 게시글입니다."));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponseBody> handleIllegalArgumentException(IllegalArgumentException exception){
        log.info("IllegalArgumentException => {}", exception.getMessage());
        return ResponseEntity.badRequest()
            .body(BaseResponseBody.of(exception.getMessage()));
    }
}
