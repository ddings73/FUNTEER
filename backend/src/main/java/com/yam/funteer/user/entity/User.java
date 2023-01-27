package com.yam.funteer.user.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.sun.istack.NotNull;
import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.common.code.UserType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "user")
@Getter @SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Email
	@Column(unique = true)
	private @NotNull String email;
	private @NotNull String password;
	private @NotNull String name;
	private @NotNull String phone;
	@OneToOne
	@JoinColumn(name = "member_profile")
	private Attach profileImg;
	private LocalDateTime regDate;
	private Long money;
	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false)
	private UserType userType;

	public void signOut(UserType userType){
		this.userType = userType;
	}
	public boolean validatePassword(PasswordEncoder passwordEncoder, String password){
		return passwordEncoder.matches(password, this.password);
	}
}
