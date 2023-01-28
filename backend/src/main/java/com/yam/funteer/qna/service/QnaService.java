package com.yam.funteer.qna.service;

import java.util.List;

import com.yam.funteer.common.code.UserType;
import com.yam.funteer.qna.entity.Qna;
import com.yam.funteer.qna.exception.QnaNotFoundException;
import com.yam.funteer.qna.request.QnaRegisterReq;
import com.yam.funteer.qna.response.QnaBaseRes;

public interface QnaService {
	List<Qna>qnaGetList(UserType userType);
	void qnaRegister(QnaRegisterReq qnaRegisterReq);
	QnaBaseRes qnaGetDetail(Long qnaId, Long userId) throws QnaNotFoundException;
	void qnaModify(Long qnaId, QnaRegisterReq qnaRegisterReq) throws QnaNotFoundException;
	void qnaDelete(Long qnaId, Long userId) throws QnaNotFoundException;
}
