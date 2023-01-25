package com.yam.funteer.donation.response;

import java.util.List;

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.post.entity.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonationGetListRes extends BaseResponseBody {


	public static DonationGetListRes of(String message){
		DonationGetListRes res=new DonationGetListRes();
		res.setMessage(message);

		return res;
	}
}