package com.yam.funteer.donation.dto.request;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.yam.funteer.donation.entity.Donation;
import com.yam.funteer.pay.entity.Payment;
import com.yam.funteer.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DonationJoinReq {
	@NotBlank
	private String paymentAmount;

	public Payment toPaymentEntity(Long paymentAmount, User user, Donation donation){
		return Payment.builder()
			.user(user)
			.amount(paymentAmount)
			.post(donation)
			.payDate(LocalDateTime.now())
			.build();
	}
}
