package com.yam.funteer.faq.dto.request;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.post.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FaqRegisterReq {
	@NotBlank
	private String title;
	@NotBlank
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
