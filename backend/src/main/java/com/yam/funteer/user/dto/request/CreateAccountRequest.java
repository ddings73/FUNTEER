package com.yam.funteer.user.dto.request;

import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.common.code.UserType;
import com.yam.funteer.user.entity.Member;
import com.yam.funteer.user.entity.Team;
import lombok.*;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor @ToString
public class CreateAccountRequest {

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    @Pattern(regexp = "(01[016789])-(\\d{3,4})-(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
    private String phone;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String nickname;

    @NotNull
    private UserType userType;

    private Attach vmsFile;
    private Attach performFile;
    private String description;
    public boolean isTeam(){
        return userType.equals(UserType.TEAM);
    }

    public Member toMember(){
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .phone(phone)
                .nickname(nickname)
                .userType(userType)
                .money(0L)
                .display(true)
                .regDate(LocalDateTime.now())
                .build();
    }

    public Team toTeam(){
        return Team.builder()
                .email(email)
                .password(password)
                .name(name)
                .phone(phone)
                .userType(userType)
                .money(0L)
                .regDate(LocalDateTime.now())
                .discription(description)
                .build();
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }
}
