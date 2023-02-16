package com.yam.funteer.user.dto.request.member;

import com.yam.funteer.attach.FileType;
import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.common.code.UserType;
import com.yam.funteer.user.dto.request.CreateAccountRequest;
import com.yam.funteer.user.entity.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter @SuperBuilder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateMemberRequest extends CreateAccountRequest {
    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    protected String nickname;


    public Member toMember(Attach profileImg){
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .phone(phone)
                .nickname(nickname)
                .profileImg(profileImg)
                .userType(UserType.NORMAL)
                .money(0L)
                .display(true)
                .regDate(LocalDateTime.now())
                .build();
    }

}
