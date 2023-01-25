package com.yam.funteer.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@Getter @ToString
@RequiredArgsConstructor
public enum UserType implements GrantedAuthority {
    NORMAL(ROLES.USER, "일반"),
    KAKAO(ROLES.USER,"카카오"),
    TEAM_AWAIT(null,"단체_대기"),
    TEAM(null,"단체_승인"),
    NORMAL_RESIGN(null,"일반_탈퇴"),
    TEAM_RESIGN(null,"단체_탈퇴"),
    ADMIN(ROLES.ADMIN, "관리자");

    public static class ROLES{
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }

    private final String authority;
    private final String description;

}
