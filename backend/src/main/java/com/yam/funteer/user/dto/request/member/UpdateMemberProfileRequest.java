package com.yam.funteer.user.dto.request.member;

import com.yam.funteer.user.dto.request.UpdateProfileRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMemberProfileRequest extends UpdateProfileRequest {
    private boolean display;
}
