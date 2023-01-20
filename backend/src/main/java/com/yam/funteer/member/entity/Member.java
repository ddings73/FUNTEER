package com.yam.funteer.member.entity;

import java.io.File;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class Member {

	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private String email;
	@NotNull
	private String name;
	@NotNull
	private String nickName;
	@NotNull
	private String phone;
	private File profileImg;
	private Long money;
	// private Code code;
	// private boolean
}
