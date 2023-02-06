package com.yam.funteer.badge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.common.code.UserType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "badge")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Badge {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private @NotBlank String name;
	@Column(nullable = false)
	private @NotBlank String description;

	@Column(nullable = false)
	private String badgeImgPath;

	@Enumerated(value = EnumType.STRING)
	private PostGroup postGroup;

	public void update(Long id,String name, String description,String badgeImgPath) {
		this.name = name;
		this.badgeImgPath=badgeImgPath;
		this.description=description;
	}
}
