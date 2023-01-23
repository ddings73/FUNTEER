package com.yam.funteer.post.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(PostHashtagId.class)
@Table(name="post_hashtag")
public class PostHashtag {
	@Id
	@ManyToOne
	@JoinColumn(name="post_id")
	private Post post;

	@Id
	@ManyToOne
	@JoinColumn(name="hashtag_id")
	private Hashtag hashtag;
}
