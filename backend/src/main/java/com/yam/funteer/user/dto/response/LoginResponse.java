package com.yam.funteer.user.dto.response;

import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.common.security.Token;
import com.yam.funteer.user.UserType;
import com.yam.funteer.user.entity.User;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse extends BaseResponseBody {

    private Long userId;
    private String username;
    private Attach profileImg;
    private UserType userType;

    // token 두개
    private Token token;

    public static LoginResponse of(User user, Token token){
        return new LoginResponse(user.getId(), user.getName(), user.getProfileImg(), user.getUserType(), token);
    }
}
