package com.yam.funteer.qna.response;

import java.util.List;

import com.yam.funteer.BaseResponseBody;
import com.yam.funteer.donation.response.DonationGetListRes;
import com.yam.funteer.post.entity.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaGetListRes extends BaseResponseBody {
	private List<Post>list;

	public static QnaGetListRes of(Integer statusCode,String message,List<Post> list){
		QnaGetListRes res=new QnaGetListRes();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setList(list);

		return res;
	}

}
