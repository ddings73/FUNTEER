package com.yam.funteer.faq.dto.request;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.faq.entity.Faq;
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
	@NotNull
	private Long groupOrPerson;

	public Faq toEntity(){
		return Faq.builder()
			.content(content)
			.regDate(LocalDateTime.now())
			.title(title)
			.groupOrPerson(groupOrPerson)
			.postGroup(PostGroup.ETC)
			.postType(PostType.FAQ).build();
	}

	public Faq toEntity(Long postId,Long faqId){
		return Faq.builder()
			.id(postId)
			.faqId(faqId)
			.content(content)
			.regDate(LocalDateTime.now())
			.title(title)
			.groupOrPerson(groupOrPerson)
			.postGroup(PostGroup.ETC)
			.postType(PostType.FAQ).build();
	}

}
