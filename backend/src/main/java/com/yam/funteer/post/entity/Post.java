package com.yam.funteer.post.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
<<<<<<< HEAD
=======
import javax.persistence.Enumerated;
>>>>>>> 48811d0f2d6861ac73b9902f928b44ed5d4711e8
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import com.yam.funteer.funding.dto.FundingRequest;
import com.yam.funteer.funding.entity.Report;
import com.yam.funteer.post.PostType;
import com.yam.funteer.user.member.entity.Member;
import com.yam.funteer.user.team.entity.Team;

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
@NoArgsConstructor
@AllArgsConstructor
public class Post {

	@Id
	@Column(name="post_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany
	@JoinColumn(name="team_id")
	private Team team;

	@OneToOne
	@JoinColumn(name="member_id")
	private Member member;

	@ManyToOne
	@JoinColumn(name="category_id")
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
	@Column(nullable = false,name="post_type")
	private PostType postType;

	@Column( name="post_reject")
	private String reject;

	@OneToMany(mappedBy = "post")
	private List<TargetMoney> targetMoney;

	@OneToOne
	@JoinColumn(name = "report_id")
	private Report report;

	public void update(FundingRequest data) {
		this.date = data.getPostDate();
	}

}
