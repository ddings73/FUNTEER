package com.yam.funteer.live.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yam.funteer.user.entity.Member;
import com.yam.funteer.user.entity.User;

import lombok.*;

@Entity
@Table(name="gift")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Gift {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "live_id")
	private Live live;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private Long amount;
	private LocalDateTime giftDate;

	public static Gift from(Live live, User user, Long amount) {
		return Gift.builder()
				.live(live)
				.user(user)
				.amount(amount)
				.giftDate(LocalDateTime.now())
				.build();
	}
}
