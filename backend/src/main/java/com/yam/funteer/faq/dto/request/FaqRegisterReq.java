package com.yam.funteer.faq.dto.request;

import java.time.LocalDateTime;

import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.post.entity.Post;

import lombok.Getter;

@Getter
public class FaqRegisterReq {
	private String title;
	private String content;

	public Post toEntity(){
		return Post.builder()
			.content(content)
			.regDate(LocalDateTime.now())
			.title(title)
			.postGroup(PostGroup.ETC)
			.postType(PostType.FAQ).build();
	}

	public Post toEntity(Long postId){
		return Post.builder()
			.id(postId)
			.content(content)
			.regDate(LocalDateTime.now())
			.title(title)
			.postGroup(PostGroup.ETC)
			.postType(PostType.FAQ).build();
	}

}
