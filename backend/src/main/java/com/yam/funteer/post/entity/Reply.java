package com.yam.funteer.post.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="reply")
public class Reply {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="reply_id")
	private Long id;

	@Column(name="reply_content")
	private String content;

	@Column(name="date")
	private LocalDateTime date;

	@ManyToOne
	private Post post;

}
