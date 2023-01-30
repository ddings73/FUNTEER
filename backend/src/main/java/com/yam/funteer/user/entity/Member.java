package com.yam.funteer.user.entity;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.common.code.UserType;

import com.yam.funteer.user.dto.request.UpdateProfileRequest;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity @Table(name = "member")
@Getter @SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends User{
	@Column(unique = true)
	private @NotBlank String nickname;
	private boolean display;

	public static Member toKakaoUser(String email, String name) {
		return Member.builder()
			.email(email)
			.name(name)
			.nickname(name)
			.display(true)
			.userType(UserType.KAKAO)
			.regDate(LocalDateTime.now())
			.build();
	}

	public void update(UpdateProfileRequest updateProfileRequest, Attach attach){
		this.nickname = updateProfileRequest.getNickname();
		this.display = updateProfileRequest.isDisplay();
		updateProfile(attach);
	}
	public void signOut(){
		super.signOut(UserType.NORMAL_RESIGN);
	}
}
