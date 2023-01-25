package com.yam.funteer.funding.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yam.funteer.post.entity.Hashtag;
import com.yam.funteer.post.entity.Post;
import com.yam.funteer.post.entity.PostHashtag;
import com.yam.funteer.post.entity.TargetMoney;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@AllArgsConstructor
@Builder
public class FundingDetailResponse {

	private Long fundingId;
	private String title;
	private String content;
	private LocalDateTime start;
	private LocalDateTime end;

	private LocalDateTime postDate;

	private List<TargetMoney> targetMonies;

	private List<PostHashtag> postHashtagList;

	public static FundingDetailResponse from(Post funding) {
		return FundingDetailResponse.builder()
			.fundingId(funding.getId())
			.title(funding.getTitle())
			.content(funding.getContent())
			.start(funding.getStart())
			.end(funding.getEnd())
			.postDate(funding.getDate())
			// .targetMonies(funding.getTargetMoney())
			// .postHashtagList(funding.getpostHashTagList)
			.build();
	}

}
