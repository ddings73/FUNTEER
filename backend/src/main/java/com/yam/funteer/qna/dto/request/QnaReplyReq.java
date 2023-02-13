package com.yam.funteer.qna.dto.request;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.yam.funteer.qna.entity.Qna;
import com.yam.funteer.qna.entity.Reply;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class QnaReplyReq {
	@NotBlank
	private String content;

	public Reply toEntity(Qna qna){
		return Reply.builder()
			.content(content)
			.qna(qna)
			.regDate(LocalDateTime.now())
			.build();
	}
}
