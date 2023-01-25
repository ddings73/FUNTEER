package com.yam.funteer.qna.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yam.funteer.post.entity.Post;
import com.yam.funteer.qna.entity.Qna;
import com.yam.funteer.qna.repository.QnaRepository;
import com.yam.funteer.qna.request.QnaMemberRegisterReq;
import com.yam.funteer.qna.request.QnaTeamRegisterReq;
import com.yam.funteer.user.UserType;
import com.yam.funteer.user.repository.MemberRepository;
import com.yam.funteer.user.repository.TeamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService {

	private final QnaRepository qnaRepository;
	private final TeamRepository teamRepository;
	private final MemberRepository memberRepository;

	@Override
	public List<Qna> qnaGetList(UserType userType){
		return qnaRepository.findAll();
	}

	@Override
	public Qna qnaTeamRegister(QnaTeamRegisterReq qnaRegisterReq) {
		Qna qna=Qna.builder()
			.password(qnaRegisterReq.getPassword())
			.content(qnaRegisterReq.getContent())
			.title(qnaRegisterReq.getTitle())
			.user(teamRepository.findById(qnaRegisterReq.getTeamId()).get())
			.regDate(LocalDateTime.now())
			.build();

		return qnaRepository.save(qna);
	}

	@Override
	public Qna qnaMemberRegister(QnaMemberRegisterReq qnaRegisterReq) {
		Qna qna=Qna.builder()
			.password(qnaRegisterReq.getPassword())
			.content(qnaRegisterReq.getContent())
			.title(qnaRegisterReq.getTitle())
			.user(teamRepository.findById(qnaRegisterReq.getMemberId()).get())
			.regDate(LocalDateTime.now())
			.build();

		return qnaRepository.save(qna);
	}

	@Override
	public Qna qnaGetDetail(Long id,String password) {
		Qna qna=qnaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());;
		if(qna.getPassword().equals(password)){
			return qna;
		}
		return null;
	}

	@Override
	public boolean qnaTeamModify(Long id,QnaTeamRegisterReq qnaTeamModifyReq) {
		Qna qna=qnaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());;

		if(qna.getUser().getId()==qnaTeamModifyReq.getTeamId()){
			qna.builder()
				.title(qnaTeamModifyReq.getTitle())
				.content(qnaTeamModifyReq.getTitle())
				.build();

			qnaRepository.save(qna);
			return true;
		}
		return false;
	}

	@Override
	public boolean qnaMemberModify(Long id,QnaMemberRegisterReq qnaMemberModifyReq) {
		Qna qna=qnaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());;

		if(qna.getUser().getId()==qnaMemberModifyReq.getMemberId()){
			qna.builder()
				.title(qnaMemberModifyReq.getTitle())
				.content(qnaMemberModifyReq.getTitle())
				.build();

			qnaRepository.save(qna);
			return true;
		}
		return false;
	}


	@Override
	public boolean qnaDelete(Long Id, Long userId) {
		Long postUserId=qnaRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException()).getUser().getId();
		if(userId==postUserId) {
			qnaRepository.deleteById(Id);
			return true;
		}
		return false;
	}
}
