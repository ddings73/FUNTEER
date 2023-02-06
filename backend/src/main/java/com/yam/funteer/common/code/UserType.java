package com.yam.funteer.common.code;

import io.openvidu.java.client.OpenViduRole;
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
    NORMAL(ROLES.USER, "일반", OpenViduRole.SUBSCRIBER),
    KAKAO(ROLES.USER,"카카오", OpenViduRole.SUBSCRIBER),
    TEAM_WAIT(ROLES.USER,"단체_대기", OpenViduRole.PUBLISHER),
    TEAM(ROLES.USER,"단체_승인", OpenViduRole.PUBLISHER),
    NORMAL_RESIGN(ROLES.USER,"일반_탈퇴", OpenViduRole.SUBSCRIBER),
    TEAM_RESIGN(ROLES.USER,"단체_탈퇴", OpenViduRole.PUBLISHER),
    ADMIN(ROLES.ADMIN,"관리자", OpenViduRole.MODERATOR);


    private final String authority;
    private final String description;
    private final OpenViduRole openviduRole;

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
