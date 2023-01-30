package com.yam.funteer.user.dto.response.member;

import com.yam.funteer.common.BaseResponseBody;

import com.yam.funteer.user.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberAccountResponse {
	private String email;
	private String name;
	private String phone;

	public static MemberAccountResponse of(Member member){
		return new MemberAccountResponse(member.getEmail(), member.getName(), member.getPhone());
	}
}
