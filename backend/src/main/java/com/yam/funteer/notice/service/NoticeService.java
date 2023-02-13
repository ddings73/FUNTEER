package com.yam.funteer.notice.service;

import java.io.IOException;
import java.util.List;

import com.yam.funteer.faq.dto.response.FaqListRes;
import com.yam.funteer.notice.dto.request.NoticeRegistReq;
import com.yam.funteer.notice.dto.response.NoticeBaseRes;
import com.yam.funteer.notice.dto.response.NoticeListRes;

public interface NoticeService {
	List<NoticeListRes>noticeGetList(int page,int size);
	NoticeBaseRes noticeGetDetail(Long postId);
	NoticeBaseRes noticeRegister(NoticeRegistReq noticeRegistReq) ;
	NoticeBaseRes noticeModify(Long postId,NoticeRegistReq noticeRegistReq) ;
	void noticeDelete(Long postId);

}
