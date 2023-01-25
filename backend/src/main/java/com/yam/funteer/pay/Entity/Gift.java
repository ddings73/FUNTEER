package com.yam.funteer.pay.Entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yam.funteer.post.entity.Post;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="gift")
public class Gift {
	@Id
	@Column(name="gift_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="gift_amount")
	private Integer amount;

	@Column(name="gift_date")
	private LocalDateTime date;

	@ManyToOne(targetEntity = Post.class)
	@JoinColumn(name="post_id")
	private Post post;
	//
	// @ManyToOne(targetEntity = Live.class)
	// @JoinColumn(name="live_id")
	// private Live live;

	// @ManyToOne(targetEntity = "team")
	// @JoinColumn
	// private Team team;
	//
	// @ManyToOne(targetEntity = "member")
	// @JoinColumn
	// private Member member;

}
