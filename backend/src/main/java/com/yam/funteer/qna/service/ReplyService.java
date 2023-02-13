package com.yam.funteer.qna.service;

import com.yam.funteer.qna.dto.request.QnaReplyReq;
import com.yam.funteer.qna.dto.response.ReplyBaseRes;
import com.yam.funteer.qna.exception.QnaNotFoundException;
import com.yam.funteer.qna.exception.ReplyNotFoundException;

public interface ReplyService {
	ReplyBaseRes replyGetDetail(Long qnaId) ;
	ReplyBaseRes replyRegister(Long qnaId, QnaReplyReq qnaReplyReq) ;
	ReplyBaseRes replyModify(Long qnaId, QnaReplyReq qnaReplyReq) ;
	void replyDelete(Long qnaId);
}
