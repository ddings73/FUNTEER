package com.yam.funteer.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseUserRequest {

    @NotNull
    private Long userId;
    private String password;

    public Optional<String> getPassword(){
        return Optional.of(password);
    }
}
