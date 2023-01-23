package com.yam.funteer.user.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SelectMemberRequest {

    @NotBlank
    private Long memberId;
    private String password;

    public Optional<String> getPassword(){
        return Optional.of(password);
    }
}
