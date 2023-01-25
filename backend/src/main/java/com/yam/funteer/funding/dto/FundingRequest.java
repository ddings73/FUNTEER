package com.yam.funteer.funding.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.yam.funteer.post.entity.Hashtag;
import com.yam.funteer.post.entity.PostHashtag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FundingRequest {

	// @NotNull
	// private Team team;

	@NotNull
	private String title;
	@NotNull
	private String content;
	@NotNull
	private LocalDateTime startDate;
	@NotNull
	private LocalDateTime endDate;
	private LocalDateTime postDate;
	private MultipartFile thumbnail;

	private MultipartFile multipartFile;

	@NotNull
	private int amount1;
	@NotNull
	private String description1;

	@NotNull
	private int amount2;
	@NotNull
	private String description2;

	@NotNull
	private int amount3;
	@NotNull
	private String description3;

	private String hashtags;

}
