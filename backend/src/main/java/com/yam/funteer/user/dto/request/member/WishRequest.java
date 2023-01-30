package com.yam.funteer.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class WishRequest {
    private @NotNull Long fundingId;
    private @NotNull Long memberId;
}
