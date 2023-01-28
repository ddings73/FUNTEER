package com.yam.funteer.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAccountRequest extends BaseUserRequest{
    private String newPassword;
    public Optional<String> getNewPassword(){
        return Optional.ofNullable(newPassword);
    }
}
