package com.yam.funteer.user.dto.request.team;

import com.yam.funteer.attach.FileType;
import com.yam.funteer.attach.FileUtil;
import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.common.code.UserType;
import com.yam.funteer.user.dto.request.CreateAccountRequest;
import com.yam.funteer.user.entity.Member;
import com.yam.funteer.user.entity.Team;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateTeamRequest extends CreateAccountRequest {

    @NotNull
    private MultipartFile vmsFile;

    @NotNull
    private MultipartFile performFile;

    public Team toTeam(){
        return Team.builder()
                .email(email)
                .password(password)
                .name(name)
                .phone(phone)
                .userType(UserType.TEAM_WAIT)
                .money(0L)
                .regDate(LocalDateTime.now())
                .build();
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void validateFile() {
        try {
            if (!FileUtil.validPdfFile(vmsFile.getInputStream()) || !FileUtil.validPdfFile(
                performFile.getInputStream())){
                throw new IllegalArgumentException("파일 형식이 올바르지 않습니다.");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

	public List<Attach> getAttachList(String vmsPath, String performPath) {
        Attach vms = Attach.of(vmsFile.getOriginalFilename(), vmsPath, FileType.VMS);
        Attach perform = Attach.of(performFile.getOriginalFilename(), performPath, FileType.PERFORM);

        return List.of(vms, perform);
	}
}
