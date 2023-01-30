package com.yam.funteer.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmailConfirmRequest {
    private String email;
    private String password;
}
