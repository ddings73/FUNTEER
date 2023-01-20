package com.yam.funteer.funding.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Getter

public class Funding {

	@Id @GeneratedValue
	Long id;

	private String title;

	private String content;

}
