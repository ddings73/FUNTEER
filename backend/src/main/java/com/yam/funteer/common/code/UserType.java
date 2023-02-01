package com.yam.funteer.common.code;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import com.yam.funteer.common.code.TypeModel;
import org.springframework.security.core.GrantedAuthority;

@ApiModel
@Getter @ToString
@RequiredArgsConstructor
public enum UserType implements TypeModel, GrantedAuthority {
    NORMAL(ROLES.USER, "일반"),
    KAKAO(ROLES.USER,"카카오"),
    TEAM_WAIT(ROLES.USER,"단체_대기"),
    TEAM(ROLES.USER,"단체_승인"),
    NORMAL_RESIGN(ROLES.USER,"일반_탈퇴"),
    TEAM_RESIGN(ROLES.USER,"단체_탈퇴"),
    ADMIN(ROLES.ADMIN,"관리자");


    private final String authority;
    private final String description;

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public static class ROLES{
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
