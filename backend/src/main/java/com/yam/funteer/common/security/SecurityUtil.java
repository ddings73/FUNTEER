package com.yam.funteer.common.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtil {

    private static final String anonymousUser = "anonymousUser";
    public static Long getCurrentUserId(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName().equals(anonymousUser)) {
            log.error("Security Context 에 인증 정보가 없습니다.");
            throw new BadCredentialsException("다시 로그인 해주세요");
        }

        Long userId = Long.valueOf(authentication.getName());
        return userId;
    }

    public static boolean isLogin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication == null || authentication.getName().equals(anonymousUser) ? false : true;
    }
}