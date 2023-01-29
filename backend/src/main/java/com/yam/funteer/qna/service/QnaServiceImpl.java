package com.yam.funteer.qna.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yam.funteer.common.code.UserType;
import com.yam.funteer.exception.UserNotFoundException;

import com.yam.funteer.qna.entity.Qna;
import com.yam.funteer.qna.entity.Reply;
import com.yam.funteer.qna.exception.QnaNotFoundException;
import com.yam.funteer.qna.repository.QnaRepository;
import com.yam.funteer.qna.repository.ReplyRepository;
import com.yam.funteer.qna.request.QnaRegisterReq;
import com.yam.funteer.qna.request.QnaReplyReq;
import com.yam.funteer.qna.response.QnaBaseRes;
import com.yam.funteer.user.entity.User;
import com.yam.funteer.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService {

	private final QnaRepository qnaRepository;
	private final UserRepository userRepository;
	private final ReplyRepository replyRepository;

	@Override
	public List<Qna> qnaGetList(UserType userType) {
		return qnaRepository.findAllByUserType(userType);
	}

	@Override
	public void qnaRegister(QnaRegisterReq qnaRegisterReq) {
		User user=userRepository.findById(qnaRegisterReq.getUserId()).orElseThrow(()->new UserNotFoundException());
		qnaRepository.save(qnaRegisterReq.toEntity(user));
	}


	@Override
	public QnaBaseRes qnaGetDetail(Long qnaId, Long userId) throws QnaNotFoundException {
		Qna qna = qnaRepository.findById(qnaId).orElseThrow(() -> new QnaNotFoundException());
		if (qna.getUser().getId()==userId) {
			return new QnaBaseRes(qna);
		}
		return null;
	}

	@Override
	public void qnaModify(Long qnaId, QnaRegisterReq qnaRegisterReq) throws QnaNotFoundException {
		Qna qna = qnaRepository.findById(qnaId).orElseThrow(() -> new QnaNotFoundException());
		User user=userRepository.findById(qnaRegisterReq.getUserId()).orElseThrow(()->new UserNotFoundException());
		if(qnaRegisterReq.getUserId()==qna.getUser().getId()) {
			qnaRepository.save(qnaRegisterReq.toEntity(user,qnaId));
		}
	}

	@Override
	public void qnaDelete(Long postId,Long userId) throws QnaNotFoundException{
		Qna qna = qnaRepository.findById(postId).orElseThrow(() -> new QnaNotFoundException());

		if(qna.getUser().getId()==userId) {
			qnaRepository.deleteById(postId);
			Reply reply=replyRepository.findByQna(qna);
			if(reply!=null){
				replyRepository.delete(reply);
			}
		}
	}

}
