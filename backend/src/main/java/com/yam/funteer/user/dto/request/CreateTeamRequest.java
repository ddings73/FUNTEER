package com.yam.funteer.user.dto.request;

import com.yam.funteer.common.code.UserType;
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
import java.time.LocalDateTime;

@Getter @SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateTeamRequest extends CreateAccountRequest{

    @NotNull
    private MultipartFile vmsFile;

    @NotNull
    private MultipartFile performFile;

    @NotNull
    private String description;

    public Team toTeam(){
        return Team.builder()
                .email(email)
                .password(password)
                .name(name)
                .phone(phone)
                .userType(UserType.TEAM_WAIT)
                .money(0L)
                .regDate(LocalDateTime.now())
                .discription(description)
                .build();
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }
}
