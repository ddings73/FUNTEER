package com.yam.funteer.qna.service;

import org.springframework.stereotype.Service;

import com.yam.funteer.alarm.repository.AlarmRepository;
import com.yam.funteer.alarm.service.AlarmService;
import com.yam.funteer.common.code.UserType;
import com.yam.funteer.common.security.SecurityUtil;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.post.entity.Post;
import com.yam.funteer.qna.dto.request.QnaReplyReq;
import com.yam.funteer.qna.dto.response.ReplyBaseRes;
import com.yam.funteer.qna.entity.Qna;
import com.yam.funteer.qna.entity.Reply;
import com.yam.funteer.qna.exception.ReplyDuplicatedException;
import com.yam.funteer.qna.exception.QnaNotFoundException;
import com.yam.funteer.qna.exception.ReplyNotFoundException;
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
	private final AlarmService alarmService;

	@Override
	public ReplyBaseRes replyGetDetail(Long qnaId) {
		Qna qna=qnaRepository.findByQnaId(qnaId).orElseThrow(()->new QnaNotFoundException());
		User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());
		Reply reply=replyRepository.findByQna(qna).orElseThrow(()->new ReplyNotFoundException());
		if(qna.getUser().getId()==user.getId()||user.getUserType().equals(UserType.ADMIN)){
			return new ReplyBaseRes(reply);
		}else throw new IllegalArgumentException("접근 권한이 없습니다.");
	}

	@Override
	public ReplyBaseRes replyRegister(Long qnaId, QnaReplyReq qnaReplyReq)  {
		Qna qna=qnaRepository.findByQnaId(qnaId).orElseThrow(()->new QnaNotFoundException());
		User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());
		if(replyRepository.findByQna(qna).isPresent())throw new ReplyDuplicatedException();
		if(user.getUserType().equals(UserType.ADMIN)) {
			Reply reply=replyRepository.save(qnaReplyReq.toEntity(qna));
			qna.setRespond();
			alarmService.send(qna.getUser().getEmail(),qna.getTitle()+" 에 대한 답변이 등록되었습니다.","/qna/"+qnaId);
			return new ReplyBaseRes(reply);
		}else throw new  IllegalArgumentException("접근 권한이 없습니다.");
	}

	@Override
	public ReplyBaseRes replyModify(Long qnaId,QnaReplyReq qnaReplyReq) {
		Qna qna=qnaRepository.findByQnaId(qnaId).orElseThrow(()->new QnaNotFoundException());
		User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());
		Reply reply=replyRepository.findByQna(qna).orElseThrow(()->new ReplyNotFoundException());
		if(user.getUserType().equals(UserType.ADMIN)){
			reply.update(reply.getId(),qna,qnaReplyReq.getContent(),reply.getRegDate());
			alarmService.send(qna.getUser().getEmail(),qna.getTitle()+" 에 대한 답변이 수정되었습니다.","/qna/"+qnaId);
			return new ReplyBaseRes(reply);
		}else throw new IllegalArgumentException("접근 권한이 없습니다.");
	}

	@Override
	public void replyDelete(Long qnaId) {
		Qna qna=qnaRepository.findByQnaId(qnaId).orElseThrow(()->new QnaNotFoundException());
		Reply reply=replyRepository.findByQna(qna).orElseThrow(()->new ReplyNotFoundException());
		User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());

		if(user.getUserType().equals(UserType.ADMIN)){
			replyRepository.delete(reply);
			alarmService.send(qna.getUser().getEmail(),qna.getTitle()+" 에 대한 답변이 삭제되었습니다.", " /qna/"+qnaId);
		}else throw new IllegalArgumentException("접근 권한이 없습니다.");
	}
}
