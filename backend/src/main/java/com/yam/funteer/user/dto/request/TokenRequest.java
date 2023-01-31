package com.yam.funteer.user.dto.request;

import lombok.Getter;

@Getter
public class TokenRequest {
    private String accessToken;
    private String refreshToken;

    public TokenRequest(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken.substring(7);
    }
}
