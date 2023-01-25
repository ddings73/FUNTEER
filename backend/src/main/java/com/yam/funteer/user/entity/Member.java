package com.yam.funteer.user.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.yam.funteer.user.UserType;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity @Table(name = "member")
@Getter @SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends User{
	private @NotBlank String nickname;
	private boolean display;

	public void toggleDisplay(){
		this.display = !this.display;
	}
	public void signOut(){
		super.signOut(UserType.NORMAL_RESIGN);
	}
}
