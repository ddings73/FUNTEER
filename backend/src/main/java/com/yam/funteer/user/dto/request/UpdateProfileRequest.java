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

	public void validateProfileType(){
		try {
			if(!FileUtil.validImgFile(this.profileImg.getInputStream()))
				throw new IllegalArgumentException("이미지파일만 업로드 할 수 있습니다.");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
