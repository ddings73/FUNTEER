package com.yam.funteer.admin.dto;

import com.yam.funteer.common.code.UserType;
import com.yam.funteer.user.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberListResponse {

	private String name;
	private String nickname;
	private String profileImgUrl;
	private String phone;
	private String email;
	private Long money;
	private UserType userType;

	public static MemberListResponse of(Member member){
		MemberListResponse response = MemberListResponse.builder()
			.name(member.getName())
			.nickname(member.getNickname())
			.phone(member.getPhone())
			.email(member.getEmail())
			.money(member.getMoney())
			.userType(member.getUserType())
			.build();

		member.getProfileImg().ifPresent(attach ->{
			response.setProfileImgUrl(attach.getPath());
		});
		return response;
	}

}
