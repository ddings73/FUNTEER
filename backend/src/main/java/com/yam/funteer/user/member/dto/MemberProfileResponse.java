package com.yam.funteer.user.member.dto;

import com.yam.funteer.BaseResponseBody;
import com.yam.funteer.user.member.entity.Member;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor @Builder
public class MemberProfileResponse extends BaseResponseBody {
    private boolean isPrivate;
    private String nickname;
    private Long money;
    private Integer likeCnt;
    private Integer followingCnt;

    public static MemberProfileResponse of(Member member){
        return MemberProfileResponse.builder()
                .nickname(member.getNickName())
                .isPrivate(member.isPrivate())
                .money(member.getMoney())
                .build();
    }
}
