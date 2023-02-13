package com.yam.funteer.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.funding.exception.CategoryNotFoundException;
import com.yam.funteer.funding.exception.CommentNotFoundException;
import com.yam.funteer.funding.exception.FundingNotFoundException;
import com.yam.funteer.funding.exception.InsufficientBalanceException;
import com.yam.funteer.funding.exception.NotAuthenticatedMemberException;
import com.yam.funteer.funding.exception.NotAuthenticatedTeamException;
import com.yam.funteer.funding.exception.NotFoundReportException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class FundingExceptionHandler {

	@ExceptionHandler(NotFoundReportException.class)
	public ResponseEntity<BaseResponseBody> NotFoundReportExceptionHandler(NotFoundReportException ex) {
		log.info("NotFoundReportException ", ex.getMessage());
		ex.printStackTrace();
		return ResponseEntity.badRequest()
			.body(BaseResponseBody.of(ex.getMessage()));
	}

	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<BaseResponseBody> InSufficientBalanceExceptionHandler(InsufficientBalanceException ex) {
		log.info("InsufficientBalanceException ", ex.getMessage());
		ex.printStackTrace();
		return ResponseEntity.badRequest()
			.body(BaseResponseBody.of(ex.getMessage()));
	}

	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<BaseResponseBody> CategoryNotFoundExceptionHandler(CategoryNotFoundException ex) {
		log.info("CategoryNotFoundException ", ex.getMessage());
		ex.printStackTrace();
		return ResponseEntity.badRequest()
			.body(BaseResponseBody.of(ex.getMessage()));
	}

	@ExceptionHandler(CommentNotFoundException.class)
	public ResponseEntity<BaseResponseBody> CommentNotFoundExceptionHandler (CommentNotFoundException ex) {
		log.info("CommentNotFoundException ", ex.getMessage());
		ex.printStackTrace();
		return ResponseEntity.badRequest()
			.body(BaseResponseBody.of(ex.getMessage()));
	}

	@ExceptionHandler(FundingNotFoundException.class)
	public ResponseEntity<BaseResponseBody> FundingNotFoundExceptionHandler (FundingNotFoundException ex) {
		log.info("FundingNotFoundException ", ex.getMessage());
		ex.printStackTrace();
		return ResponseEntity.badRequest()
			.body(BaseResponseBody.of(ex.getMessage()));
	}

	@ExceptionHandler(NotAuthenticatedMemberException.class)
	public ResponseEntity<BaseResponseBody> NotAuthenticatedMemberExceptionHandler (NotAuthenticatedMemberException ex) {
		log.info("NotAuthenticatedMemberException ", ex.getMessage());
		ex.printStackTrace();
		return ResponseEntity.badRequest()
			.body(BaseResponseBody.of(ex.getMessage()));
	}

	@ExceptionHandler(NotAuthenticatedTeamException.class)
	public ResponseEntity<BaseResponseBody> NotAuthenticatedTeamExceptionHandler (NotAuthenticatedTeamException ex) {
		log.info("NotAuthenticatedTeamException ", ex.getMessage());
		ex.printStackTrace();
		return ResponseEntity.badRequest()
			.body(BaseResponseBody.of(ex.getMessage()));
	}

}
