package com.yam.funteer.user;

import com.yam.funteer.common.CommonCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public enum UserType implements CommonCode {
    NORMAL("일반"),
    NORMAL_RESIGN("일반_탈퇴"),
    KAKAO("카카오"),
    TEAM_AWAIT("단체_대기"),
    TEAM("단체_승인"),
    TEAM_RESIGN("단체_탈퇴");

    private final String description;

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getDescription() {
        return description;
    }
}
