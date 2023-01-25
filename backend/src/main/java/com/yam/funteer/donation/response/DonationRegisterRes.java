package com.yam.funteer.donation.response;

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.post.entity.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonationRegisterRes extends BaseResponseBody {

	public static DonationRegisterRes of(String message){
		DonationRegisterRes res=new DonationRegisterRes();
		res.setMessage(message);

		return res;
	}
}
