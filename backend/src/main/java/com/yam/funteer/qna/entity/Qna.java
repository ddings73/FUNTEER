package com.yam.funteer.qna.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yam.funteer.post.entity.Post;
import com.yam.funteer.user.entity.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="qna")
@Getter @SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Qna extends Post {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false,updatable = false,nullable = false,unique = true )
	private Long qnaId;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

}
