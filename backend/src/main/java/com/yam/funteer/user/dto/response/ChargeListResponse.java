package com.yam.funteer.user.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.yam.funteer.user.entity.Charge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChargeListResponse {
	private Long amount;
	private LocalDateTime chargeDate;
	private int possibleRefund;

	public static ChargeListResponse from (Charge charge) {
		ChargeListResponse chargeListResponse = new ChargeListResponse(charge.getAmount(), charge.getChargeDate(), charge.getPossibleRefund());
		return chargeListResponse;
	}


}
