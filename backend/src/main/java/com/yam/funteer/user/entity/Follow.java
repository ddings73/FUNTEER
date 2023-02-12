package com.yam.funteer.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "follow")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "member_id")
	private @NotNull Member member;
	@ManyToOne
	@JoinColumn(name = "team_id")
	private @NotNull Team team;
	private Boolean checked;

	public void toggle(){
		this.checked = !this.checked;
	}
	private Follow(Member member, Team team){
		this.member = member;
		this.team = team;
		this.checked = true;
	}
	public static Follow of(Member member, Team team){
		return new Follow(member, team);
	}
}
