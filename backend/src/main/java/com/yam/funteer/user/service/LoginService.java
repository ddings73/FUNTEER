package com.yam.funteer.user.service;

import com.yam.funteer.user.dto.LoginRequest;
import com.yam.funteer.user.dto.LoginResponse;

public interface LoginService {
    LoginResponse processLogin(LoginRequest loginRequest);
}
