package com.yam.funteer.common.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.aspectj.apache.bcel.classfile.Code;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Attach {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attach_id")
	private Long id;

	@Column(name = "attach_name")
	private String name;

	@Column(name = "attach_path")
	private String path;

	@Column(name = "attach_uploaddate")
	private LocalDateTime uploadDate;

//	@Column(name = "attach_code")
	// 공통 코드
//	private GroupCode code;
}
