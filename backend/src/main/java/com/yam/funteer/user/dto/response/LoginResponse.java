package com.yam.funteer.user.dto.response;

import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.common.BaseResponseBody;
import com.yam.funteer.user.entity.User;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse extends BaseResponseBody {

    private Long userId;
    private String username;
    private Attach profileImg;
    private String userType;

    // token 두개
    private String accessToken;
    private String refreshToken;

    public static LoginResponse of(User user, String accessToken, String refreshToken){
        return new LoginResponse(user.getId(), user.getName(), user.getProfileImg(), user.getUserType().name()
            , accessToken, refreshToken);
    }
}
