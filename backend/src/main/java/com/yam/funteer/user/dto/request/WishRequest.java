package com.yam.funteer.user.dto.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishRequest {
	private @NotNull Long fundingId;
	private @NotNull Long memberId;
}
