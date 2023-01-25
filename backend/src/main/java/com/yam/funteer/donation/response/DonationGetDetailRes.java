package com.yam.funteer.donation.response;

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.post.entity.Post;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DonationGetDetailRes extends BaseResponseBody {

	public static DonationGetDetailRes of ( String message) {
		DonationGetDetailRes res = new DonationGetDetailRes();
		res.setMessage(message);
		return res;
	}
}