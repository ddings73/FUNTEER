package com.yam.funteer.live.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.exception.SessionNotFoundException;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.user.entity.Member;

import com.yam.funteer.user.entity.Team;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name = "live")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Live {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "funding_id")
	private Funding funding;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String sessionId;

	@OneToOne
	@JoinColumn(name = "attach_id")
	private Attach attach;

	public static Live of(String sessionId, Funding funding) {
		return Live.builder()
				.sessionId(sessionId)
				.funding(funding)
				.startTime(LocalDateTime.now())
				.build();
	}

	@Transactional(noRollbackFor = SessionNotFoundException.class)
	public void end() {
		this.endTime = LocalDateTime.now();
	}

	public Team getTeam() {
		return funding.getTeam();
	}

	public void saveFile(Attach attach) {
		this.attach = attach;
	}
}
