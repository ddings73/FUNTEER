package com.yam.funteer.post.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(PostHashtagId.class)
@Table(name="post_hashtag")
@NoArgsConstructor
@AllArgsConstructor
public class PostHashtag {
	@Id
	@ManyToOne
	//@JoinColumn(name="post_id")
	private Post post;

	@Id
	@ManyToOne
	//@JoinColumn(name="hashtag_id")
	private Hashtag hashtag;

}
