package com.yam.funteer.user.service;

public interface UserService {

    void confirmEmail(String email);
    void confirmName(String name);
    void confirmNickname(String nickname);
}
