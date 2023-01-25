package com.yam.funteer.qna.response;

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.post.entity.Post;

public class QnaGetDetailRes extends BaseResponseBody {

	public static QnaGetDetailRes of(String message){
		QnaGetDetailRes res=new QnaGetDetailRes();
		res.setMessage(message);

		return res;
	}
}
