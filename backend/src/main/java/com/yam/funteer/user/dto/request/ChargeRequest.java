package com.yam.funteer.user.dto.request;

import com.yam.funteer.user.entity.Charge;
import com.yam.funteer.user.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChargeRequest extends BaseUserRequest{
	private Long amount;
	public Charge toEntity(Member member){
		return Charge.builder()
				.member(member)
				.amount(this.amount)
				.chargeDate(LocalDateTime.now())
				.build();
	}
}
