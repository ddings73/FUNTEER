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
    KAKAO("카카오"),
    TEAM("단체");

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
