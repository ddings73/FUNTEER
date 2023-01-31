package com.yam.funteer.user.dto.response.team;

import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.user.entity.Team;
import lombok.*;


@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamAccountResponse {
    private String email;
    private String name;
    private String phone;
    private String vmsFileUrl;
    private String performFileUrl;

    private String profileImg;

    public static TeamAccountResponse of(Team team){
        return TeamAccountResponse.builder()
                .email(team.getEmail())
                .name(team.getName())
                .phone(team.getPhone())
                .build();
    }

    public static TeamAccountResponse from(Team team) {

        // Attach attach = team.getProfileImg().get();

        return TeamAccountResponse.builder()
            .email(team.getEmail())
            .name(team.getName())
            .phone(team.getPhone())
            // .profileImg(attach.getPath())
            .build();
    }
}
