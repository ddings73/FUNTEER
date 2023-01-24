package com.yam.funteer.user.dto;

import com.yam.funteer.user.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    private String email;
    private String password;
    private UserType type;

    public boolean isTeam(){
        return type.equals(UserType.TEAM);
    }
}
