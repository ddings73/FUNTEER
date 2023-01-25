package com.yam.funteer.qna.service;

import java.util.List;

import com.yam.funteer.post.entity.Post;
import com.yam.funteer.qna.entity.Qna;
import com.yam.funteer.qna.request.QnaMemberRegisterReq;
import com.yam.funteer.qna.request.QnaTeamRegisterReq;
import com.yam.funteer.user.UserType;

public interface QnaService {
	List<Qna>qnaGetList(UserType userType);
	Qna qnaTeamRegister(QnaTeamRegisterReq qnaRegisterReq);
	Qna qnaMemberRegister(QnaMemberRegisterReq qnaRegisterReq);
	Qna qnaGetDetail(Long id,String password);
	boolean qnaTeamModify(Long Id, QnaTeamRegisterReq qnaTeamRegisterReq);
	boolean qnaMemberModify(Long Id, QnaMemberRegisterReq qnaMemberRegisterReq);
	boolean qnaDelete(Long Id,Long userId);
}
