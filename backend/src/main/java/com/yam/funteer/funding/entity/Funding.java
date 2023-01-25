package com.yam.funteer.funding.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yam.funteer.funding.dto.FundingRequest;
import com.yam.funteer.post.entity.Post;
import com.yam.funteer.post.entity.PostHashtag;
import com.yam.funteer.user.entity.Team;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="funding")
@Getter @SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Funding extends Post {
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String rejectComment;

	public void setHashtags(List<PostHashtag> postHashtagList) {
	}

	public void update(FundingRequest data) {
	}

	public void setEnd(LocalDateTime endDate) {
	}
}
