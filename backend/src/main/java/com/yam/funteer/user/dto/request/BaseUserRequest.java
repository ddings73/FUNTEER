package com.yam.funteer.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseUserRequest {

    @NotNull
    private Long userId;
    private String password;

    public Optional<String> getPassword(){
        return Optional.ofNullable(password);
    }
}
