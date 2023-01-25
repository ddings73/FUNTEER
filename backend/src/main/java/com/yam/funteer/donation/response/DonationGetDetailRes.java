package com.yam.funteer.donation.response;

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.post.entity.Post;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DonationGetDetailRes extends BaseResponseBody {
	private Post post;

	public static DonationGetDetailRes of (String message, Post post) {
		DonationGetDetailRes res = new DonationGetDetailRes();
		res.setMessage(message);
		res.setPost(post);

		return res;
	}
}