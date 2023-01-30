package com.yam.funteer.funding.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import com.yam.funteer.common.security.SecurityUtil;
import com.yam.funteer.post.entity.Comment;
import com.yam.funteer.user.entity.Member;
import com.yam.funteer.user.repository.MemberRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class CommentResponse {

	private String memberNickName;
	private String content;

	private LocalDateTime regDate;
	public static CommentResponse from(Comment comment) {
		return new CommentResponse(comment.getMember().getNickname(), comment.getContent(), comment.getRegDate());
	}
}
