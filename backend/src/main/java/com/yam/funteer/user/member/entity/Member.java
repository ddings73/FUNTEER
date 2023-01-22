package com.yam.funteer.user.member.entity;

import java.io.File;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import com.yam.funteer.common.entity.Attach;
import com.yam.funteer.user.UserType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "member")
@RequiredArgsConstructor
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@NotNull
	@Column(name = "member_email")
	private String email;

	@NotBlank
	@Column(name = "member_name")
	private String name;

	@NotBlank
	@Column(name = "member_nickname")
	private String nickName;

	@NotBlank
	@Column(name = "member_phone")
	private String phone;

//	@Column(name = "member_image")
//	private Attach profileImg;

	@Column(name = "member_money")
	private Long money;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "member_code")
	 private UserType code;

	@Column(name = "member_private")
	 private boolean publishProfile;
}
