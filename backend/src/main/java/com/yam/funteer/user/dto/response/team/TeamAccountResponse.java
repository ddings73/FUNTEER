package com.yam.funteer.user.dto.response.team;

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

    public static TeamAccountResponse of(Team team){
        return TeamAccountResponse.builder()
                .email(team.getEmail())
                .name(team.getName())
                .phone(team.getPhone())
                .build();
    }
}
