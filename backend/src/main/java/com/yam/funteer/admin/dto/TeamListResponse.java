package com.yam.funteer.admin.dto;

import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.attach.entity.TeamAttach;
import com.yam.funteer.user.entity.Team;

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
public class TeamListResponse {
	private String name;
	private String email;
	private String phone;
	private String vmsFileUrl;
	private String performFileUrl;

	public static TeamListResponse of(Team team, String vmsFileUrl, String performFileUrl) {
		return TeamListResponse.builder()
			.email(team.getEmail())
			.name(team.getName())
			.phone(team.getPhone())
			.vmsFileUrl(vmsFileUrl)
			.performFileUrl(performFileUrl)
			.build();
	}
}
