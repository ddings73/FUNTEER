package com.yam.funteer.user.dto.response;

import java.util.List;

import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.user.entity.Team;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor @Builder
public class TeamProfileResponse extends BaseResponseBody {
    private String name;
    private String description;
    private Long money;
    private List<Funding> fundingList;
    private long followerCnt;
    private Attach banner;

    public static TeamProfileResponse of(Team team, List<Funding> fundingList, long follwerCnt){
        return TeamProfileResponse.builder()
                .name(team.getName())
                .description(team.getDiscription())
                .money(team.getMoney())
                .fundingList(fundingList)
                .followerCnt(follwerCnt)
                .banner(team.getBanner())
                .build();
    }
}
