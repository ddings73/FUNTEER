package com.yam.funteer.qna.request;


import java.time.LocalDateTime;

import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.qna.entity.Qna;
import com.yam.funteer.user.entity.User;
import com.yam.funteer.user.repository.UserRepository;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QnaRegisterReq {

	private Long userId;
	private String title;
	private String content;

	public Qna toEntity(User user){
		return Qna.builder()
			.title(title)
			.content(content)
			.user(user)
			.regDate(LocalDateTime.now())
			.postGroup(PostGroup.ETC)
			.postType(PostType.QNA).build();
	}

	public Qna toEntity(User user,Long postId){
		return Qna.builder()
			.id(postId)
			.title(title)
			.content(content)
			.user(user)
			.regDate(LocalDateTime.now())
			.postGroup(PostGroup.ETC)
			.postType(PostType.QNA).build();
	}

}
