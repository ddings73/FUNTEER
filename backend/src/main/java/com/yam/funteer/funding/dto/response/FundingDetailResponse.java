package com.yam.funteer.funding.dto.response;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.yam.funteer.common.code.PostType;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.post.entity.PostHashtag;
import com.yam.funteer.post.repository.CommentRepository;
import com.yam.funteer.user.dto.response.team.TeamAccountResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Builder
@Getter
public class FundingDetailResponse {

	private final CommentRepository commentRepository;

	private TeamAccountResponse team;

	private Long wishCount;

	private String fundingDescription;

	private Long fundingId;
	private String title;
	private String content;

	private String category;
	private Long categoryId;

	private LocalDate startDate;
	private LocalDate endDate;

	private LocalDateTime postDate;

	private TargetMoneyResponse targetMoneyListLevelOne;
	private TargetMoneyResponse targetMoneyListLevelTwo;
	private TargetMoneyResponse targetMoneyListLevelThree;

	private Optional<HashtagResponse> postHashtagList;

	private Boolean isWished;

	private String thumbnail;

	private Optional<Page<CommentResponse>> comments;

	private String currentFundingAmount;

	private int hit;

	private Long participatedCount;

	private PostType postType;

	public static FundingDetailResponse from(Funding funding) {

		DecimalFormat decFormat = new DecimalFormat("###,###");
		String str = decFormat.format(funding.getCurrentFundingAmount());

		TeamAccountResponse team = TeamAccountResponse.from(funding.getTeam());

		FundingDetailResponse response = FundingDetailResponse.builder()
			.team(team)
			.fundingId(funding.getFundingId())
			.category(funding.getCategory().getName())
			.categoryId(funding.getCategory().getId())
			.title(funding.getTitle())
			.content(funding.getContent())
			.startDate(funding.getStartDate())
			.endDate(funding.getEndDate())
			.postDate(funding.getRegDate())
			.thumbnail(funding.getThumbnail())
			.currentFundingAmount(str)
			.fundingDescription(funding.getFundingDescription())
			.postType(funding.getPostType())
			.hit(funding.getHit())
			.build();

		// funding.getComments().ifPresent(comments -> {
		// 	Optional<List<CommentResponse>> collect = Optional.of(comments.stream()
		// 		.map(comment -> CommentResponse.from(comment))
		// 		.collect(Collectors.toList()));
		// 	response.setComments(collect);
		// });


		funding.getPostHashtags().ifPresent(postHashtags -> {
			Optional<HashtagResponse> hashtagList = Optional.of(HashtagResponse.from(postHashtags));
			response.setPostHashtagList(hashtagList);
		});

		return response;
	}

}
