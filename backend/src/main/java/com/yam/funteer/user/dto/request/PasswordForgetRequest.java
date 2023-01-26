package com.yam.funteer.user.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordForgetRequest extends BaseUserRequest {
	private @NotBlank String newPassword;
}
