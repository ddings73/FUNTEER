package com.yam.funteer.user.dto.request.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class FollowRequest {
    private @NotNull Long memberId;
    private @NotNull Long teamId;
}
