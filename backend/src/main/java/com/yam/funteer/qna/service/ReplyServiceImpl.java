package com.yam.funteer.qna.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.yam.funteer.common.code.UserType;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.qna.entity.Qna;
import com.yam.funteer.qna.entity.Reply;
import com.yam.funteer.qna.exception.QnaNotFoundException;
import com.yam.funteer.qna.repository.QnaRepository;
import com.yam.funteer.qna.repository.ReplyRepository;
import com.yam.funteer.qna.request.QnaReplyReq;
import com.yam.funteer.qna.response.ReplyBaseRes;
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
	public ReplyBaseRes replyGetDetail(Long replyId, Long userId) throws QnaNotFoundException {
		Reply reply=replyRepository.findById(replyId).orElseThrow(()->new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
		User user=userRepository.findById(userId).orElseThrow(()->new UserNotFoundException());

		Long qnaUserId=reply.getQna().getUser().getId();
		if(qnaUserId==userId||user.getUserType().equals(UserType.ADMIN)){
			return new ReplyBaseRes(reply);
		}else throw new IllegalArgumentException("접근 권한이 없습니다.");
	}

	@Override
	public void replyRegister(Long qnaId,QnaReplyReq qnaReplyReq) throws QnaNotFoundException {
		Qna qna=qnaRepository.findById(qnaId).orElseThrow(()->new QnaNotFoundException());
		User user=userRepository.findById(qnaReplyReq.getUserId()).orElseThrow(()->new UserNotFoundException());
		if(user.getUserType().equals(UserType.ADMIN)) {
			replyRepository.save(qnaReplyReq.toEntity(qna));
		}else throw new  IllegalArgumentException("접근 권한이 없습니다.");
	}

	@Override
	public void replyModify(Long replyId,QnaReplyReq qnaReplyReq) throws QnaNotFoundException {
		Reply reply=replyRepository.findById(replyId).orElseThrow(()->new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
		User user=userRepository.findById(qnaReplyReq.getUserId()).orElseThrow(()->new UserNotFoundException());
		Qna qna=qnaRepository.findById(reply.getQna().getId()).orElseThrow(()->new QnaNotFoundException());

		if(user.getUserType().equals(UserType.ADMIN)) {
			reply.update(replyId,qna,qnaReplyReq.getContent(),reply.getRegDate());
		}else throw new IllegalArgumentException("접근 권한이 없습니다.");
	}

	@Override
	public void replyDelete(Long replyId,Long userId) {
		Reply reply=replyRepository.findById(replyId).orElseThrow(()->new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
		User user=userRepository.findById(userId).orElseThrow(()->new UserNotFoundException());

		if(user.getUserType().equals(UserType.ADMIN)) {
			replyRepository.delete(reply);
		}else throw new IllegalArgumentException("접근 권한이 없습니다.");
	}
}
