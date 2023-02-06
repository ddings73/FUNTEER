package com.yam.funteer.faq.service;

import java.util.List;

import com.yam.funteer.faq.dto.request.FaqRegisterReq;
import com.yam.funteer.faq.dto.response.FaqBaseRes;
import com.yam.funteer.faq.dto.response.FaqListRes;

public interface FaqService {
	List<FaqListRes>faqGetList(int page,int size);
	FaqBaseRes faqGetDetail(Long postId);
	FaqBaseRes faqRegister(FaqRegisterReq faqRegisterReq);
	FaqBaseRes faqModify(Long postId,FaqRegisterReq faqRegisterReq);
	void faqDelete(Long postId);
}
