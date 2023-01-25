package com.yam.funteer.qna.response;

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.post.entity.Post;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class QnaGetDetailRes extends BaseResponseBody {
	Post post;

	public static QnaGetDetailRes of(Integer statusCode,String message, Post post){
		QnaGetDetailRes res=new QnaGetDetailRes();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setPost(post);

		return res;
	}
}
