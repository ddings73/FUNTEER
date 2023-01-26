package com.yam.funteer.post.noticeFaq.dto.request;

import java.time.LocalDateTime;

import com.yam.funteer.post.PostGroup;
import com.yam.funteer.post.PostType;
import com.yam.funteer.post.entity.Post;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class NoticeRegistReq {
	private String title;
	private String content;

	public Post toEntity(){
		return Post.builder()
			.title(title)
			.content(content)
			.postGroup(PostGroup.ETC)
			.postType(PostType.NOTICE)
			.regDate(LocalDateTime.now())
			.build();
	}
}
