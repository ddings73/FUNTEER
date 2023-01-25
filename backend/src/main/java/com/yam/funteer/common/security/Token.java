package com.yam.funteer.common.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString @Getter
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    private String token;
    private String refreshToken;

}
