package com.yam.funteer.user.dto.request;

import com.yam.funteer.user.entity.Charge;
import com.yam.funteer.user.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChargeRequest{

	@NotNull
	private Long userId;
	private Long amount;
	public Charge toEntity(Member member){
		return Charge.builder()
				.member(member)
				.amount(this.amount)
				.chargeDate(LocalDateTime.now())
				.build();
	}
}
