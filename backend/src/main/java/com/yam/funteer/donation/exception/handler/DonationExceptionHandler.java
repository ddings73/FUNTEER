package com.yam.funteer.donation.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.donation.exception.DonationNotFoundException;
import com.yam.funteer.donation.exception.DonationPayException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice("com.yam.funteer.donation")
public class DonationExceptionHandler {

    @ExceptionHandler({DonationNotFoundException.class})
    public ResponseEntity<BaseResponseBody> handleDonationNotFoundException(DonationNotFoundException exception){
        log.info("QnaNotFoundException => {}", exception.getMessage());
        return ResponseEntity.badRequest()
            .body(BaseResponseBody.of("존재하지 않는 도네이션페이지 입니다."));
    }

    @ExceptionHandler({DonationPayException.class})
    public ResponseEntity<BaseResponseBody>handleDonationPayException(DonationPayException
        exception){
        log.info("DonationPayException => {}",exception.getMessage());
        return ResponseEntity.badRequest().body(BaseResponseBody.of("금액이 부족합니다."));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponseBody> handleIllegalArgumentException(IllegalArgumentException exception){
        log.info("IllegalArgumentException => {}", exception.getMessage());
        return ResponseEntity.badRequest()
            .body(BaseResponseBody.of(exception.getMessage()));
    }
}
