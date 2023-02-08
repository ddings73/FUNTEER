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
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberListResponse {

	private long totalElements;
	private int totalPages;
	private List<MemberInfo> userList;

	public static MemberListResponse of(Page<Member> memberPages){
		List<MemberInfo> list = memberPages.stream().map(MemberInfo::of).collect(Collectors.toList());
		long totalElements = memberPages.getTotalElements();
		int totalPages1 = memberPages.getTotalPages();
		return new MemberListResponse(totalElements, totalPages1, list);
	}

	@Builder
	@Getter @Setter
	@NoArgsConstructor
	@AllArgsConstructor
	private static class MemberInfo {
		private Long userId;
		private String name;
		private String nickname;
		private String profileImgUrl;
		private String phone;
		private String email;
		private Long money;
		private UserType userType;

		public static MemberInfo of(Member member){
			MemberInfo memberInfo = MemberInfo.builder()
					.userId(member.getId())
					.name(member.getName())
					.nickname(member.getNickname())
					.phone(member.getPhone())
					.email(member.getEmail())
					.money(member.getMoney())
					.userType(member.getUserType())
					.build();

			member.getProfileImg().ifPresent(attach ->{
				memberInfo.setProfileImgUrl(attach.getPath());
			});
			return memberInfo;
		}
	}
}
