package com.yam.funteer.attach.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.yam.funteer.user.entity.Team;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "team_attach")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamAttach {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "attach_id")
	private Attach attach;
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

	private boolean confirm;

	public static TeamAttach of(Team team, Attach attach) {
		return TeamAttach.builder()
			.team(team)
			.attach(attach)
			.confirm(false)
			.build();
	}

	public void updateAttach(Attach attach) {
		this.attach.update(attach.getName(), attach.getPath());
	}


	public void submit() {
		this.confirm = true;
	}
}
