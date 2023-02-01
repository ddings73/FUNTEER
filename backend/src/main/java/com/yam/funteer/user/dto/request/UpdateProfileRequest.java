package com.yam.funteer.user.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.yam.funteer.attach.FileType;
import com.yam.funteer.attach.FileUtil;
import com.yam.funteer.attach.entity.Attach;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileRequest{
	@NotNull
	private Long userId;

	protected MultipartFile profileImg;

    public Attach getProfile(String filename) {
		return Attach.builder()
				.name(profileImg.getOriginalFilename())
				.path(filename)
				.fileType(FileType.OTHER)
				.regDate(LocalDateTime.now())
				.build();
    }

	public void validateProfile(){
		try {
			if(!FileUtil.validImgFile(profileImg.getInputStream()))
				throw new IllegalArgumentException("이미지파일만 업로드 할 수 있습니다.");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
