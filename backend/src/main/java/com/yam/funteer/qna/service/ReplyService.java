package com.yam.funteer.qna.service;

import com.yam.funteer.qna.exception.QnaNotFoundException;
import com.yam.funteer.qna.request.QnaReplyReq;
import com.yam.funteer.qna.response.ReplyBaseRes;

public interface ReplyService {
	ReplyBaseRes replyGetDetail(Long qnaId,Long userId) throws QnaNotFoundException;
	void replyRegister(Long qnaId,QnaReplyReq qnaReplyReq) throws QnaNotFoundException;
	void replyModify(Long replyId,QnaReplyReq qnaReplyReq) throws QnaNotFoundException;
	void replyDelete(Long replyId,Long userId);
}
