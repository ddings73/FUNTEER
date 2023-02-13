package com.yam.funteer.funding.dto.response;

import java.time.LocalDateTime;
import com.yam.funteer.post.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class CommentResponse {

	private Long commentId;
	private String memberProfileImg;

	private String memberNickName;
	private String content;

	private LocalDateTime regDate;

	public static CommentResponse from(Comment comment) {
		return new CommentResponse(comment.getId(), comment.getMember().getProfileImg().get().getPath(), comment.getMember().getNickname(), comment.getContent(), comment.getRegDate());
	}
}
