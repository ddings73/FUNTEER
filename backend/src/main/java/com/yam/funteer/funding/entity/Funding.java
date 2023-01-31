package com.yam.funteer.funding.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.scheduling.annotation.Scheduled;

import com.yam.funteer.common.code.PostType;
import com.yam.funteer.post.entity.Comment;
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
	private LocalDate startDate;
	private LocalDate endDate;
	private String rejectComment;

	private Long currentFundingAmount;

	private String fundingDescription;

	public void setTargetMoneyList(List<TargetMoney> targetMoneyList) {
		this.targetMoneyList = targetMoneyList;
	}

	public void setHashtags(List<PostHashtag> hashtags) {
		this.hashtags = hashtags;
	}

	@OneToMany(mappedBy = "funding", cascade = CascadeType.ALL)
	private List<TargetMoney> targetMoneyList;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<PostHashtag> hashtags;

	@OneToMany(mappedBy = "funding", cascade = CascadeType.ALL)
	private List<Comment> comments;

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public void setCategory(Category category) {

		this.category = category;
	}

	public void setTeam(Team team) {

		this.team = team;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setRejectComment(String rejectComment) {
		this.rejectComment = rejectComment;
	}

	public void setCurrentFundingAmount(Long amount) {
		this.currentFundingAmount = amount;
	}
}
