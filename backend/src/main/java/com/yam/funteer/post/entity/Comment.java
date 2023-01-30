package com.yam.funteer.post.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.user.entity.Member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="comment")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Comment {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "funding_id")
	private Funding funding;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	private @NotBlank String content;
	private LocalDateTime regDate;

	public Comment(Funding funding, Member member, String content) {
		this.funding = funding;
		this.member = member;
		this.content = content;
		this.regDate = LocalDateTime.now();

	}
}