package com.yam.funteer.user.service;

import com.yam.funteer.user.dto.request.LoginRequest;
import com.yam.funteer.user.dto.response.LoginResponse;

public interface LoginService {
    LoginResponse processLogin(LoginRequest loginRequest);
}
