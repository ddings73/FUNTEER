package com.yam.funteer.donation.response;

import com.yam.funteer.BaseResponseBody;
import com.yam.funteer.post.entity.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonationRegisterRes extends BaseResponseBody {
	private Post post;

	public static DonationRegisterRes of(Integer statusCode,String message, Post post){
		DonationRegisterRes res=new DonationRegisterRes();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setPost(post);

		return res;
	}
}
