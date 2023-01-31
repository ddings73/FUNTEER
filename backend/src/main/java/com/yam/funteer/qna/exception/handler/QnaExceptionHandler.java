package com.yam.funteer.qna.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.yam.funteer.qna.exception.QnaNotFoundException;
import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.qna.exception.ReplyDuplicatedException;
import com.yam.funteer.qna.exception.ReplyNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice("com.yam.funteer.qna")
public class QnaExceptionHandler {

    @ExceptionHandler({QnaNotFoundException.class})
    public ResponseEntity<BaseResponseBody> handleQnaNotFoundException(QnaNotFoundException exception){
        log.info("QnaNotFoundException => {}", exception.getMessage());
        return ResponseEntity.badRequest()
            .body(BaseResponseBody.of("존재하지 않는 게시글입니다."));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponseBody> handleIllegalArgumentException(IllegalArgumentException exception){
        log.info("IllegalArgumentException => {}", exception.getMessage());
        return ResponseEntity.badRequest()
            .body(BaseResponseBody.of(exception.getMessage()));
    }

    @ExceptionHandler({ReplyDuplicatedException.class})
    public ResponseEntity<BaseResponseBody>handleReplyDuplicatedException(ReplyDuplicatedException
        exception){
        log.info("ReplyDuplicatedException => {}",exception.getMessage());
        return ResponseEntity.badRequest().body(BaseResponseBody.of("이미 답변이 있는 게시글입니다."));
    }

    @ExceptionHandler({ReplyNotFoundException.class})
    public ResponseEntity<BaseResponseBody> handleReplyNotFoundException(ReplyNotFoundException exception){
        log.info("ReplyNotFoundException => {}", exception.getMessage());
        return ResponseEntity.badRequest()
            .body(BaseResponseBody.of("존재하지 않는 게시글입니다."));
    }
}
