package com.yam.funteer.user.dto.request.member;

import com.yam.funteer.common.code.PostGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MileageDetailRequest {

    private Long userId;
    private PostGroup postGroup;

}
