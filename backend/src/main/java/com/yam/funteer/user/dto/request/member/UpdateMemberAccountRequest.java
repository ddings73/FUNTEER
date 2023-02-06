package com.yam.funteer.user.dto.request.member;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.yam.funteer.user.dto.request.BaseUserRequest;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMemberAccountRequest extends BaseUserRequest {

	private String newPassword;

	public Optional<String> getNewPassword(){
		return Optional.ofNullable(newPassword);
	}
}
