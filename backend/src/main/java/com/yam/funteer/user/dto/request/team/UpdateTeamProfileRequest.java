package com.yam.funteer.user.dto.request.team;

import com.yam.funteer.attach.FileType;
import com.yam.funteer.attach.FileUtil;
import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.user.dto.request.UpdateProfileRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTeamProfileRequest extends UpdateProfileRequest {

    private String description;
    private MultipartFile banner;

    public Attach getBanner(String path){
        return Attach.builder()
                .name(banner.getOriginalFilename())
                .path(path)
                .fileType(FileType.OTHER)
                .regDate(LocalDateTime.now())
                .build();
    }
    public void validateBannerAndProfile(){
        validateProfile();
        try {
            if(!FileUtil.validImgFile(banner.getInputStream()))
                throw new IllegalArgumentException("이미지파일만 업로드 할 수 있습니다.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
