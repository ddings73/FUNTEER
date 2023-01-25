package com.yam.funteer.user.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity @Table(name = "member")
@Getter @SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends User{
	private @NotBlank String nickname;
	private boolean display;
}
