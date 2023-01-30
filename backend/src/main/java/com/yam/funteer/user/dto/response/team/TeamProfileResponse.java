package com.yam.funteer.user.dto.response.team;

import java.util.List;

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
    private String profileImgUrl;
    private String bannerUrl;

    public static TeamProfileResponse of(Team team, List<Funding> fundingList, long follwerCnt){
        TeamProfileResponse response = TeamProfileResponse.builder()
                .name(team.getName())
                .description(team.getDescription())
                .money(team.getMoney())
                .fundingList(fundingList)
                .followerCnt(follwerCnt)
                .build();

        team.getProfileImg().ifPresent(attach -> response.setProfileImgUrl(attach.getPath()));
        team.getBanner().ifPresent(attach -> response.setBannerUrl(attach.getPath()));
        return response;
    }
}
