package com.yam.funteer.qna.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.yam.funteer.common.code.UserType;
import com.yam.funteer.common.security.SecurityUtil;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.qna.dto.request.QnaReplyReq;
import com.yam.funteer.qna.dto.response.ReplyBaseRes;
import com.yam.funteer.qna.entity.Qna;
import com.yam.funteer.qna.entity.Reply;
import com.yam.funteer.qna.exception.QnaNotFoundException;
import com.yam.funteer.qna.repository.QnaRepository;
import com.yam.funteer.qna.repository.ReplyRepository;

import com.yam.funteer.user.entity.User;
import com.yam.funteer.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

	private final UserRepository userRepository;
	private final QnaRepository qnaRepository;
	private final ReplyRepository replyRepository;

	@Override
	public ReplyBaseRes replyGetDetail(Long qnaId) throws QnaNotFoundException {
		Qna qna=qnaRepository.findById(qnaId).orElseThrow(()->new QnaNotFoundException());
		User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());
		Integer number=replyRepository.countAllByQna(qna);
		if(number>0&&(qna.getUser().getId()==user.getId()||user.getUserType().equals(UserType.ADMIN))){
			Reply reply=replyRepository.findByQna(qna);
			return new ReplyBaseRes(reply);
		}else throw new IllegalArgumentException("접근 권한이 없습니다.");
	}

	@Override
	public ReplyBaseRes replyRegister(Long qnaId, QnaReplyReq qnaReplyReq) throws QnaNotFoundException {
		Qna qna=qnaRepository.findById(qnaId).orElseThrow(()->new QnaNotFoundException());
		User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());
		Integer number=replyRepository.countAllByQna(qna);
		if(user.getUserType().equals(UserType.ADMIN)&&number==0) {
			Reply reply=replyRepository.save(qnaReplyReq.toEntity(qna));
			return new ReplyBaseRes(reply);
		}else throw new  IllegalArgumentException("접근 권한이 없습니다.");
	}

	@Override
	public ReplyBaseRes replyModify(Long qnaId,QnaReplyReq qnaReplyReq) throws QnaNotFoundException {
		Qna qna=qnaRepository.findById(qnaId).orElseThrow(()->new QnaNotFoundException());
		Integer number=replyRepository.countAllByQna(qna);
		User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());

		if(number>0&&(qna.getUser().getId()==user.getId()||user.getUserType().equals(UserType.ADMIN))){
			Reply reply=replyRepository.findByQna(qna);
			reply.update(reply.getId(),qna,qnaReplyReq.getContent(),reply.getRegDate());
			return new ReplyBaseRes(reply);
		}else throw new IllegalArgumentException("접근 권한이 없습니다.");
	}

	@Override
	public void replyDelete(Long qnaId) throws QnaNotFoundException {
		Qna qna=qnaRepository.findById(qnaId).orElseThrow(()->new QnaNotFoundException());
		Integer number=replyRepository.countAllByQna(qna);
		User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());

		if(number>0&&(qna.getUser().getId()==user.getId()||user.getUserType().equals(UserType.ADMIN))){
			Reply reply=replyRepository.findByQna(qna);
			replyRepository.delete(reply);
		}else throw new IllegalArgumentException("접근 권한이 없습니다.");
	}
}
