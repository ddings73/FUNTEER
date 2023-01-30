package com.yam.funteer.qna.service;

import com.yam.funteer.qna.dto.request.QnaReplyReq;
import com.yam.funteer.qna.dto.response.ReplyBaseRes;
import com.yam.funteer.qna.exception.QnaNotFoundException;

public interface ReplyService {
	ReplyBaseRes replyGetDetail(Long qnaId) throws QnaNotFoundException;
	ReplyBaseRes replyRegister(Long qnaId, QnaReplyReq qnaReplyReq) throws QnaNotFoundException;
	ReplyBaseRes replyModify(Long qnaId,QnaReplyReq qnaReplyReq) throws QnaNotFoundException;
	void replyDelete(Long qnaId) throws QnaNotFoundException;
}
