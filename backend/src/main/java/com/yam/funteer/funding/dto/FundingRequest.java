package com.yam.funteer.funding.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.yam.funteer.common.entity.Attach;
import com.yam.funteer.post.entity.TargetMoney;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FundingRequest {

	@NotNull
	private Long FundingId;
	@NotNull
	private String title;
	@NotNull
	private String content;
	@NotNull
	private LocalDateTime startDate;
	@NotNull
	private LocalDateTime endDate;
	@NotNull
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

}
