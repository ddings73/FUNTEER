package com.yam.funteer.qna.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yam.funteer.common.code.UserType;
import com.yam.funteer.qna.dto.request.QnaRegisterReq;
import com.yam.funteer.qna.dto.response.QnaBaseRes;
import com.yam.funteer.qna.dto.response.QnaListRes;
import com.yam.funteer.qna.entity.Qna;
import com.yam.funteer.qna.exception.QnaNotFoundException;


public interface QnaService {
	List<QnaListRes>qnaGetList();
	QnaBaseRes qnaRegister(QnaRegisterReq qnaRegisterReq,List<MultipartFile>files);
	QnaBaseRes qnaGetDetail(Long qnaId) throws QnaNotFoundException;
	QnaBaseRes qnaModify(Long qnaId, QnaRegisterReq qnaRegisterReq,List<MultipartFile>files) throws
		QnaNotFoundException;
	void qnaDelete(Long qnaId) throws QnaNotFoundException;
}
