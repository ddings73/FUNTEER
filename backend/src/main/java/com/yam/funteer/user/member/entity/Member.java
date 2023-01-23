package com.yam.funteer.user.member.entity;

import java.io.File;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import com.yam.funteer.common.entity.Attach;
import com.yam.funteer.user.UserType;
import lombok.*;

@Entity
@Table(name = "member")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Email
	@Column(name = "member_email", unique = true)
	private @NotNull String email;

	@Column(name = "member_password")
	private @NotNull String password;

	@Column(name = "member_name")
	private @NotNull String name;

	@Column(name = "member_nickname", unique = true)
	private @NotNull String nickName;

	@Column(name = "member_phone")
	private @NotNull String phone;

//	@Column(name = "member_image")
//	@OneToOne(targetEntity = Attach.class)
//	private Attach profileImg;

	@Column(name = "member_money")
	private Long money;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "member_code", nullable = false)
	 private UserType status;

	@Column(name = "member_private")
	private boolean isPrivate;

	@Column(name = "member_regdate")
	private LocalDateTime regDate;

	public void setSignOut() {
		this.status = UserType.NORMAL_OUT;
	}
}
