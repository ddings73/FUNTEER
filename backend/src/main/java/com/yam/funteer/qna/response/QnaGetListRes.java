package com.yam.funteer.qna.response;

import com.yam.funteer.common.BaseResponseBody;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaGetListRes extends BaseResponseBody {

	public static QnaGetListRes of(String message){
		QnaGetListRes res=new QnaGetListRes();
		res.setMessage(message);
		return res;
	}

}
