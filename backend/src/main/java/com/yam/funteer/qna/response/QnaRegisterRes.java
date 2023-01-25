package com.yam.funteer.qna.response;

import com.yam.funteer.common.BaseResponseBody;

public class QnaRegisterRes extends BaseResponseBody {
	public static QnaRegisterRes of(String message){
		QnaRegisterRes res=new QnaRegisterRes();
		res.setMessage(message);
		return res;
	}

}
