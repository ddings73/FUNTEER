package com.yam.funteer.qna.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yam.funteer.post.entity.Post;
import com.yam.funteer.post.repository.PostRepository;
import com.yam.funteer.qna.request.QnaMemberRegisterReq;
import com.yam.funteer.qna.request.QnaTeamRegisterReq;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService {

	private final PostRepository postRepository;

	// public List<Post> qnaGetList(GroupCode postcode,GroupCode membercode)
	public List<Post> qnaGetList(){
		// return postRepository.findAllByCodeAndCode(postcode,membercode);
		return postRepository.findAll();
	}


	public Post qnaTeamRegister(QnaTeamRegisterReq qnaRegisterReq) {
		// Post post=new Post();
		// post.setContent(qnaRegisterReq.getContent());
		// post.setTitle(qnaRegisterReq.getTitle());
		// post.setPassword(qnaRegisterReq.getPassword());
		// post.setDate(LocalDateTime.now());
		// post.setHit(Long.valueOf(0));

		// Team team=new Team();
		// team.setId(qnaRegisterReq.getTeamId());
		//
		//post.setTeam(qnaRegisterReq.getTeam());

		// return postRepository.save(post);
		return null;
	}


	public Post qnaMemberRegister(QnaMemberRegisterReq qnaRegisterReq) {
		// Post post=new Post();
		// post.setContent(qnaRegisterReq.getContent());
		// post.setTitle(qnaRegisterReq.getTitle());
		// post.setPassword(qnaRegisterReq.getPassword());
		// post.setDate(LocalDateTime.now());
		// post.setHit(Long.valueOf(0));

		// Member member=new Member();
		// member.setId(qnaRegisterReq.getMemberId());
		//
		// post.setMember();

		// return postRepository.save(post);
		return null;
	}


	public Post qnaGetDetail(Long id) {
		return postRepository.findById(id).get();
	}


	public Post qnaTeamModify(Long id,QnaTeamRegisterReq qnaTeamModifyReq) {
		Post post=postRepository.findById(id).get();

		// if(post.getTeam().getId()==qnaTeamModifyReq.getTeamId()) {
		// 	post.setTitle(qnaTeamModifyReq.getTitle());
		// 	post.setPassword(qnaTeamModifyReq.getPassword());
		// 	post.setContent(qnaTeamModifyReq.getContent());
		// 	post.setDate(LocalDateTime.now());

			return postRepository.save(post);
		// }
		// return post;
	}

	public Post qnaMemberModify(Long id,QnaMemberRegisterReq qnaMemberModifyReq) {

		Post post=postRepository.findById(id).get();

		// if(post.getMember().getId()==qnaMemberModifyReq.getMemberId()) {

			// post.setTitle(qnaMemberModifyReq.getTitle());
			// post.setPassword(qnaMemberModifyReq.getPassword());
			// post.setContent(qnaMemberModifyReq.getContent());
			// post.setDate(LocalDateTime.now());

			return postRepository.save(post);
		// }
		// return post;
	}
}
