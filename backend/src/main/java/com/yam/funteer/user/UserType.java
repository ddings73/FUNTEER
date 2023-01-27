package com.yam.funteer.user;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@ApiModel
@Getter @ToString
@RequiredArgsConstructor
public enum UserType {
    NORMAL("일반"),
    KAKAO("카카오"),
    TEAM_WAIT("단체_대기"),
    TEAM("단체_승인"),
    NORMAL_RESIGN("일반_탈퇴"),
    TEAM_RESIGN("단체_탈퇴"),
    ADMIN("관리자");

    private final String description;

}
