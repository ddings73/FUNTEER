package com.yam.funteer.pay.entity;

import java.time.LocalDateTime;

import javax.naming.Name;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yam.funteer.post.entity.Post;
import com.yam.funteer.user.entity.Member;

import com.yam.funteer.user.entity.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "payment")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	private Long amount;
	private LocalDateTime payDate;

	public String getUserEmail(){
		return user.getEmail();
	}
}
