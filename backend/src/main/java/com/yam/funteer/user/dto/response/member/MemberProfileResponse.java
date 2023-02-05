package com.yam.funteer.user.dto.response.member;

import com.yam.funteer.badge.entity.Badge;
import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.user.entity.Member;
import com.yam.funteer.user.entity.UserBadge;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor @Builder
public class MemberProfileResponse {
    private boolean display;
    private String nickname;
    private Long money;
    private List<MyBadge> myBadges;
    private long wishCnt;
    private long followingCnt;
    private String profileImgUrl;

    public static MemberProfileResponse of(Member member, long wishCnt, long followingCnt, List<UserBadge> userBadgeList){
        List<MyBadge> myBadges = userBadgeList.stream().map(MyBadge::make).collect(Collectors.toList());
        MemberProfileResponse response = MemberProfileResponse.builder()
                .nickname(member.getNickname())
                .display(member.isDisplay())
                .money(member.getMoney())
                .wishCnt(wishCnt)
                .followingCnt(followingCnt)
                .myBadges(myBadges)
                .build();

        member.getProfileImg().ifPresent(attach -> response.setProfileImgUrl(attach.getPath()));
        return response;
    }

    @Getter @AllArgsConstructor
    private static class MyBadge{
        private String name;
        private String description;
        private String badgeImgPath;
        private boolean achieve;

        public static MyBadge make(UserBadge userBadge){
            Badge badge = userBadge.getBadge();
            return new MyBadge(badge.getName(), badge.getDescription(), badge.getBadgeImgPath(), userBadge.isAchieve());
        }
    }
}
