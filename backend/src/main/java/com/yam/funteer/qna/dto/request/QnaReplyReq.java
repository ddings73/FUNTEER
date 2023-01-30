package com.yam.funteer.qna.dto.request;

import java.time.LocalDateTime;

import com.yam.funteer.qna.entity.Qna;
import com.yam.funteer.qna.entity.Reply;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QnaReplyReq {
	private String content;

	@Builder
	public QnaReplyReq(Long userId,String content){
		this.content=content;
	}

	public Reply toEntity(Qna qna){
		return Reply.builder()
			.content(content)
			.qna(qna)
			.regDate(LocalDateTime.now())
			.build();
	}
}
