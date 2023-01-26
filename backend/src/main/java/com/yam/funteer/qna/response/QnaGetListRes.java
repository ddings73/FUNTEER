package com.yam.funteer.qna.response;

import java.util.List;

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.post.entity.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaGetListRes extends BaseResponseBody {
	private List<Post>list;

	public static QnaGetListRes of(String message,List<Post> list){
		QnaGetListRes res=new QnaGetListRes();
		res.setMessage(message);
		res.setList(list);

		return res;
	}

}
