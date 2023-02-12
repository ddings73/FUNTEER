package com.yam.funteer.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordUpdateRequest {
	private String name;
	private String email;
	private String password;
}
