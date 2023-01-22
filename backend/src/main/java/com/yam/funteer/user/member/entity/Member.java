package com.yam.funteer.member.entity;

import java.io.File;

import javax.persistence.*;

import com.sun.istack.NotNull;

import com.yam.funteer.code.CommonCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@NotNull
	@Column(name = "member_email")
	private String email;

	@NotNull
	@Column(name = "member_name")
	private String name;

	@NotNull
	@Column(name = "member_nickname")
	private String nickName;

	@NotNull
	@Column(name = "member_phone")
	private String phone;

	@Column(name = "member_image")
	private File profileImg;

	@Column(name = "member_money")
	private Long money;

	@Column(name = "member_code")
	 private CommonCode code;

	@Column(name = "member_private")
	 private boolean publishProfile;
}
