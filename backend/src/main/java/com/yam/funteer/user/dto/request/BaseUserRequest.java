package com.yam.funteer.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Optional;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseUserRequest {

    @NotNull
    private Long userId;
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    public Optional<String> getPassword(){
        return Optional.ofNullable(password);
    }
}
