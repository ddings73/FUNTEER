package com.yam.funteer.user.dto;

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
    private Integer likeCnt;
    private Integer followingCnt;

    public static MemberProfileResponse of(Member member){
        return MemberProfileResponse.builder()
                .nickname(member.getNickname())
                .display(member.isDisplay())
                .money(member.getMoney())
                .build();
    }
}
