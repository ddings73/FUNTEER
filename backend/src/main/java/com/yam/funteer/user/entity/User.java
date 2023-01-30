package com.yam.funteer.user.entity;

import java.time.LocalDateTime;
import java.util.Optional;

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
	private String password;
	private @NotNull String name;
	private String phone;
	@OneToOne
	@JoinColumn(name = "profile_id")
	private Attach profileImg;
	private LocalDateTime regDate;
	private Long money;
	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false)
	private UserType userType;

	public Optional<Attach> getProfileImg(){
		return Optional.ofNullable(this.profileImg);
	}
	protected void updateProfile(Attach profileImg){
		this.profileImg = profileImg;
	}
	public void charge(Long amount) {
		this.money += amount;
	}
	public void changePassword(String password){
		this.password = password;
	}
	public void signOut(UserType userType){
		this.userType = userType;
	}
	public boolean isResign(){return this.userType.equals(UserType.NORMAL_RESIGN) || this.userType.equals(UserType.TEAM_RESIGN);}
	public void validatePassword(PasswordEncoder passwordEncoder, String password){
		if(!passwordEncoder.matches(password, this.password))
			throw new IllegalArgumentException();
	}

	public void setMoney(long amount) {
		this.money = amount;
	}

}
