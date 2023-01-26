package com.yam.funteer.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChargeRequest extends BaseUserRequest{
	private Long amount;
}
