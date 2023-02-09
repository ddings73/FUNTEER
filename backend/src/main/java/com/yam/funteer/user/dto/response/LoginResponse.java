package com.yam.funteer.user.dto.response;

import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.user.entity.Token;
import com.yam.funteer.common.code.UserType;
import com.yam.funteer.user.entity.User;

import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse extends BaseResponseBody{

    private Long userId;
    private String username;
    private String profileImgUrl;
    private UserType userType;
    private TokenInfo token;

    public static LoginResponse of(User user, TokenInfo token){
        String path = null;
        if(user.getProfileImg().isPresent()){
            path = user.getProfileImg().get().getPath();
        }

        return LoginResponse.builder()
                .userId(user.getId())
                .username(user.getName())
                .profileImgUrl(path)
                .userType(user.getUserType())
                .token(token)
                .build();
    }
}
