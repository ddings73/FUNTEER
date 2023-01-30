package com.yam.funteer.user.dto.response;

import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.user.entity.Member;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor @Builder
public class MemberProfileResponse extends BaseResponseBody {
    private boolean display;
    private String nickname;
    private Long money;
    private long wishCnt;
    private long followingCnt;
    private String profileImgUrl;

    public static MemberProfileResponse of(Member member, long wishCnt, long followingCnt){
        MemberProfileResponse response = MemberProfileResponse.builder()
                .nickname(member.getNickname())
                .display(member.isDisplay())
                .money(member.getMoney())
                .wishCnt(wishCnt)
                .followingCnt(followingCnt)
                .build();

        member.getProfileImg().ifPresent(attach -> response.setProfileImgUrl(attach.getPath()));
        return response;
    }
}
