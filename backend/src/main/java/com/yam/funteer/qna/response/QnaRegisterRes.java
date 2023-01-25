package com.yam.funteer.qna.response;

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.post.entity.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaRegisterRes extends BaseResponseBody {
	private Post post;

	public static QnaRegisterRes of(String message,Post post){
		QnaRegisterRes res=new QnaRegisterRes();
		res.setMessage(message);
		res.setPost(post);

		return res;
	}

}
