package com.yam.funteer.user.dto;

import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.user.entity.Member;
import com.yam.funteer.user.entity.Team;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private Long userId;
    private String username;
    private Attach profileImg;

    // token 두개
    private String accessToken;
    private String refreshToken;

    public static LoginResponse of(Member member, String accessToken, String refreshToken){
        return new LoginResponse(member.getId(), member.getName(), member.getProfileImg(), accessToken, refreshToken);
    }

    public static LoginResponse of(Team team, String accessToken, String refreshToken){
        return new LoginResponse(team.getId(), team.getName(), team.getProfileImg(), accessToken, refreshToken);
    }
}
