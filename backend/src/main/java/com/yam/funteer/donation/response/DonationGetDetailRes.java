package com.yam.funteer.donation.response;

import com.yam.funteer.BaseResponseBody;
import com.yam.funteer.post.entity.Post;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DonationGetDetailRes extends BaseResponseBody {
	private Post post;

	public static DonationGetDetailRes of (Integer statusCode, String message, Post post) {
		DonationGetDetailRes res = new DonationGetDetailRes();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setPost(post);

		return res;
	}
}