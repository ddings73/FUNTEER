package com.yam.funteer.post.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.yam.funteer.code.GroupCode;
import com.yam.funteer.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="post")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	@Id
	@Column(name="post_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// @OneToOne
	// @JoinColumn(name="team_id")
	// private Team team;

	@OneToOne
	@JoinColumn(name="member_id")
	private Member member;

	@ManyToOne
	private Category category;

	@Column(nullable = false,name="post_title")
	private String title;

	@Column(nullable = false,name="post_content")
	private String content;

	@Column(nullable = false,name="post_date")
	private LocalDateTime date;

	@Column(nullable = false,name="post_hit")
	private Long hit;

	@Column(name="post_thumbnail")
	private String thumbnail;

	@Column(name="post_password")
	private String password;

	@Column(name="post_start")
	private LocalDateTime start;

	@Column(name="post_end")
	private LocalDateTime end;

	@Enumerated
	@Column(nullable = false,name="post_code")
	private GroupCode code;

	@Column( name="post_reject")
	private String reject;

}
