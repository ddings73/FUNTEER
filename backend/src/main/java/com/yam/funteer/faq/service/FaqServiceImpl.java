package com.yam.funteer.faq.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yam.funteer.attach.entity.PostAttach;
import com.yam.funteer.attach.repository.PostAttachRepository;
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.common.code.UserType;
import com.yam.funteer.common.security.SecurityUtil;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.faq.dto.request.FaqRegisterReq;
import com.yam.funteer.faq.dto.response.FaqBaseRes;
import com.yam.funteer.faq.dto.response.FaqListRes;
import com.yam.funteer.post.entity.Post;
import com.yam.funteer.post.repository.PostRepository;
import com.yam.funteer.user.entity.User;
import com.yam.funteer.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FaqServiceImpl implements  FaqService{

	private final UserRepository userRepository;
	private final PostRepository postRepository;
	private final PostAttachRepository postAttachRepository;

	@Override
	public List<FaqListRes> faqGetList() {
		List<Post>faqList=postRepository.findAllByPostType(PostType.FAQ);
		List<FaqListRes>faqListRes;
		faqListRes=faqList.stream().map(faq->new FaqListRes(faq)).collect(Collectors.toList());
		return faqListRes;
	}

	@Override
	public FaqBaseRes faqGetDetail(Long postId) {
		Post post=postRepository.findById(postId).orElseThrow();
		return new FaqBaseRes(post);
	}

	@Override
	public FaqBaseRes faqRegister(FaqRegisterReq faqRegisterReq) {
		User user=userRepository.findById(SecurityUtil.getCurrentUserId().orElseThrow()).orElseThrow(()->new UserNotFoundException());

		if(user.getUserType().equals(UserType.ADMIN)){
			Post post=postRepository.save(faqRegisterReq.toEntity());
			return new FaqBaseRes(post);
		}
		return null;
	}

	@Override
	public FaqBaseRes faqModify(Long postId,FaqRegisterReq faqRegisterReq) {
		User user=userRepository.findById(SecurityUtil.getCurrentUserId().orElseThrow()).orElseThrow(()->new UserNotFoundException());
		Post postOrigin=postRepository.findById(postId).orElseThrow();
		if(user.getUserType().equals(UserType.ADMIN)){
			Post post=postRepository.save(faqRegisterReq.toEntity(postOrigin.getId()));
			return new FaqBaseRes(post);
		}
		return null;
	}

	@Override
	public void faqDelete(Long postId) {
		User user=userRepository.findById(SecurityUtil.getCurrentUserId().orElseThrow()).orElseThrow(()->new UserNotFoundException());
		Post post=postRepository.findById(postId).orElseThrow();
		if(user.getUserType().equals(UserType.ADMIN)) {
			postRepository.deleteById(postId);
		}
	}
}
