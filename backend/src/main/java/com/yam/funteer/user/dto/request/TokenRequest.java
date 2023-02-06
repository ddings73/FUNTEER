package com.yam.funteer.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequest {
    private String accessToken;
    private String refreshToken;

    public void setAccessToken(String authorization) {
        this.accessToken = authorization.substring(7);
    }
}
