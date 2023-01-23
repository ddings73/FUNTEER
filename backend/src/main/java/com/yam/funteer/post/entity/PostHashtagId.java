package com.yam.funteer.post.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PostHashtagId implements Serializable {
	private Long post;
	private Long hashtag;
}
