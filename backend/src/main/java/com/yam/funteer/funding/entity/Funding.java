package com.yam.funteer.funding.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.boot.util.LambdaSafe;

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

	public void setTargetMoneyList(List<TargetMoney> targetMoneyList) {
		this.targetMoneyList = targetMoneyList;
	}

	public void setHashtags(List<PostHashtag> hashtags) {
		this.hashtags = hashtags;
	}

	@OneToMany(mappedBy = "funding")
	private List<TargetMoney> targetMoneyList;

	@OneToMany(mappedBy = "post")
	private List<PostHashtag> hashtags;

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public void setCategory(Category category) {

		this.category = category;
	}

	public void setTeam(Team team) {

		this.team = team;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public void setRejectComment(String rejectComment) {
		this.rejectComment = rejectComment;
	}

	public void update(FundingRequest data) {

	}
}
