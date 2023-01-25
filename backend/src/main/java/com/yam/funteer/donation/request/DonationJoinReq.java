package com.yam.funteer.donation.request;

import java.time.LocalDateTime;

import com.yam.funteer.pay.entity.Payment;
import com.yam.funteer.pay.repository.PaymentRepository;
import com.yam.funteer.post.entity.Post;
import com.yam.funteer.user.entity.Member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonationJoinReq {
	private Long memberId;
	private Long paymentAmount;

}
