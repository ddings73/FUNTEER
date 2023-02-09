package com.yam.funteer.user.dto.request.team;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.yam.funteer.user.dto.request.BaseUserRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTeamAccountRequest extends BaseUserRequest {

	private String newPassword;
	private MultipartFile vmsFile;
	private MultipartFile performFile;

	public Optional<String> getNewPassword(){
		return Optional.ofNullable(newPassword);
	}

	public Optional<MultipartFile> getVmsFile() {
		return Optional.ofNullable(vmsFile);
	}

	public Optional<MultipartFile> getPerformFile() {
		return Optional.ofNullable(performFile);
	}

	public boolean hasFile() {
		return vmsFile != null || performFile != null;
	}
}
