package com.yam.funteer.donation.response;

import java.util.List;

import com.yam.funteer.BaseResponseBody;
import com.yam.funteer.post.entity.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonationGetListRes extends BaseResponseBody {

	private List<Post>list;

	public static DonationGetListRes of(Integer statusCode,String message,List<Post> list){
		DonationGetListRes res=new DonationGetListRes();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setList(list);

		return res;
	}
}