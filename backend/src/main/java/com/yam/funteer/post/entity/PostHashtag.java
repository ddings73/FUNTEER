package com.yam.funteer.post.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="post_hashtag")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostHashtag {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;

	@ManyToOne
	@JoinColumn(name = "hashtag_id")
	private Hashtag hashtag;

	public PostHashtag(Post post, Hashtag hashtag) {
		this.post = post;
		this.hashtag = hashtag;
	}
}
