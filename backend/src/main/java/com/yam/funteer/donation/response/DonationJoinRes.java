package com.yam.funteer.donation.response;

import com.yam.funteer.common.BaseResponseBody;

import lombok.Getter;
	import lombok.Setter;

@Getter
@Setter
public class DonationJoinRes extends BaseResponseBody {

	public static DonationJoinRes of(String message){
		DonationJoinRes res=new DonationJoinRes();
		res.setMessage(message);
		return res;
	}
}
