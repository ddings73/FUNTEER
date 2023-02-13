package com.yam.funteer.user.service;

import java.util.Map;

import com.yam.funteer.user.dto.request.ForgetEmailRequest;
import com.yam.funteer.user.dto.request.PasswordUpdateRequest;

public interface UserService {

    void confirmEmail(String email);
    void confirmName(String name);
    void confirmNickname(String nickname);
	void confirmPhone(String phone);
	Map<String, String> findEmail(ForgetEmailRequest request);

	void updatePassword(PasswordUpdateRequest request);
}
