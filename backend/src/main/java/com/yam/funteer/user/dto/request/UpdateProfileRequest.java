package com.yam.funteer.user.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.yam.funteer.attach.FileType;
import com.yam.funteer.attach.entity.Attach;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileRequest extends BaseUserRequest{
	private @NotBlank String nickname;
	private @NotNull MultipartFile profileImg;
	private boolean display;

    public Attach getAttach(String filename) {
		return Attach.builder()
				.name(profileImg.getOriginalFilename())
				.path(filename)
				.fileType(FileType.OTHER)
				.regDate(LocalDateTime.now())
				.build();
    }
}
