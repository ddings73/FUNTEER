package com.yam.funteer.user.dto.response;

import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.common.security.Token;
import com.yam.funteer.common.code.UserType;
import com.yam.funteer.user.entity.User;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse extends BaseResponseBody {

    private Long userId;
    private String username;
    private String profileImgUrl;
    private UserType userType;
    private Token token;

    public static LoginResponse of(User user, Token token){
        String path = null;
        if(user.getProfileImg().isPresent()){
            path = user.getProfileImg().get().getPath();
        }

        return new LoginResponse(user.getId(), user.getName(), path, user.getUserType(), token);
    }
}
