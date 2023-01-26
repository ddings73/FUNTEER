package com.yam.funteer.user.dto.response;

import com.yam.funteer.common.BaseResponseBody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse extends BaseResponseBody {
	private String email;
	private String name;
	private String phone;
}
