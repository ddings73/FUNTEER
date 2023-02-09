package com.yam.funteer.user.dto.response.team;

import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.user.entity.Team;
import lombok.*;


@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamAccountResponse {
    private Long id;
    private String email;
    private String name;
    private String phone;
    private String vmsFileUrl;
    private String performFileUrl;
    private String profileImgUrl;

    public static TeamAccountResponse of(Team team){
        return TeamAccountResponse.builder()
            .id(team.getId())
            .email(team.getEmail())
            .name(team.getName())
            .phone(team.getPhone())
            .build();
    }

    public static TeamAccountResponse from(Team team) {

        TeamAccountResponse response = TeamAccountResponse.builder()
            .email(team.getEmail())
            .name(team.getName())
            .phone(team.getPhone())
            .build();

        team.getProfileImg().ifPresent(attach -> response.setProfileImgUrl(attach.getPath()));

        return response;
    }
}
