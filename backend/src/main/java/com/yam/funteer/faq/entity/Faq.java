package com.yam.funteer.faq.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.yam.funteer.post.entity.Post;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="faq")
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Faq extends Post {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false,updatable = false,nullable = false,unique = true )
	private Long faqId;
	private Long groupOrPerson;
}
