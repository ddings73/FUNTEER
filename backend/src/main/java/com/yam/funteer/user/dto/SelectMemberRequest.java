package com.yam.funteer.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SelectMemberRequest {

    @NotNull
    private Long memberId;
    private String password;

    public Optional<String> getPassword(){
        return Optional.of(password);
    }
}
