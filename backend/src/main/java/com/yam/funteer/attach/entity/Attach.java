package com.yam.funteer.attach.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yam.funteer.attach.FileType;

import com.yam.funteer.user.dto.request.UpdateProfileRequest;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attach")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attach {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String path;

	private LocalDateTime regDate;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private FileType fileType;

	public void update(String name, String path) {
		this.name = name;
		this.path = path;
	}

	public static Attach of(String filename, String path, FileType fileType){
		return Attach.builder()
			.name(filename)
			.fileType(fileType)
			.path(path)
			.regDate(LocalDateTime.now())
			.build();
	}
}
