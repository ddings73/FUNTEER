package com.yam.funteer.faq.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
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
import com.yam.funteer.faq.entity.Faq;
import com.yam.funteer.faq.exception.FaqNotFoundException;
import com.yam.funteer.faq.repository.FaqRepository;
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
	private final FaqRepository faqRepository;

	@Override
	public List<FaqListRes> faqGetList(int page,int size) {
		PageRequest pageRequest=PageRequest.of(page,size);
		List<Faq>faqList=faqRepository.findAllByOrderByFaqId(pageRequest);
		List<FaqListRes>faqListRes;
		faqListRes=faqList.stream().map(faq->new FaqListRes(faq)).collect(Collectors.toList());
		return faqListRes;
	}

	@Override
	public FaqBaseRes faqGetDetail(Long faqId) {
		Faq faq=faqRepository.findByFaqId(faqId).orElseThrow(()->new FaqNotFoundException());
		return new FaqBaseRes(faq);
	}

	@Override
	public FaqBaseRes faqRegister(FaqRegisterReq faqRegisterReq) {
		User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());

		if(user.getUserType().equals(UserType.ADMIN)){
			Faq faq =faqRepository.save(faqRegisterReq.toEntity());
			return new FaqBaseRes(faq);
		}
		else throw new IllegalArgumentException("접근권한이 없습니다.");
	}

	@Override
	public FaqBaseRes faqModify(Long faqId,FaqRegisterReq faqRegisterReq) {
		User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());
		Faq faqOrigin=faqRepository.findByFaqId(faqId).orElseThrow(()->new FaqNotFoundException());
		if(user.getUserType().equals(UserType.ADMIN)){
			Faq faq=faqRepository.save(faqRegisterReq.toEntity(faqOrigin.getId(),faqOrigin.getFaqId()));
			return new FaqBaseRes(faq);
		}
		else throw new IllegalArgumentException("접근권한이 없습니다.");
	}

	@Override
	public void faqDelete(Long faqId) {
		User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());
		Faq faq=faqRepository.findByFaqId(faqId).orElseThrow(()->new FaqNotFoundException());
		if(user.getUserType().equals(UserType.ADMIN)) {
			faqRepository.deleteById(faq.getId());
		}else throw new IllegalArgumentException("접근권한이 없습니다.");
	}
}
