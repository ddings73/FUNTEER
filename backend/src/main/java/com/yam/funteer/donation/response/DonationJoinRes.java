package com.yam.funteer.donation.response;

import com.yam.funteer.BaseResponseBody;
	import com.yam.funteer.pay.Entity.Payment;

	import lombok.Getter;
	import lombok.Setter;

@Getter
@Setter
public class DonationJoinRes extends BaseResponseBody {
	private Payment payment;

	public static DonationJoinRes of(Integer statusCode,String message, Payment payment){
		DonationJoinRes res=new DonationJoinRes();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setPayment(payment);

		return res;
	}
}
