package com.yam.funteer.live.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.user.entity.Member;

import lombok.*;

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

	public static Live of(String sessionId, Funding funding) {
		return Live.builder()
				.sessionId(sessionId)
				.funding(funding)
				.startTime(LocalDateTime.now())
				.build();
	}

	public void end() {
		this.endTime = LocalDateTime.now();
	}
}
