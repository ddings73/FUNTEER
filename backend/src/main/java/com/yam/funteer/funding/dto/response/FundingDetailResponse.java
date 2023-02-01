package com.yam.funteer.funding.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.post.entity.PostHashtag;
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

	private TeamAccountResponse team;

	private Long wishCount;

	private String fundingDescription;

	private Long fundingId;
	private String title;
	private String content;

	private String category;

	private LocalDate start;
	private LocalDate end;

	private LocalDateTime postDate;

	private List<TargetMoneyResponse> targetMoneyListLevelOne;
	private List<TargetMoneyResponse> targetMoneyListLevelTwo;
	private List<TargetMoneyResponse> targetMoneyListLevelThree;

	private Optional<HashtagResponse> postHashtagList;

	private String thumbnail;

	private Optional<List<CommentResponse>> comments;

	private Long currentFundingAmount;

	public static FundingDetailResponse from(Funding funding) {

		TeamAccountResponse team = TeamAccountResponse.from(funding.getTeam());

		return FundingDetailResponse.builder()
			.team(team)
			.fundingId(funding.getId())
			.category(funding.getCategory().getName())
			.title(funding.getTitle())
			.content(funding.getContent())
			.start(funding.getStartDate())
			.end(funding.getEndDate())
			.postDate(funding.getRegDate())
			.thumbnail(funding.getThumbnail())
			.currentFundingAmount(funding.getCurrentFundingAmount())
			.fundingDescription(funding.getFundingDescription())
			.build();

		// funding.getComments().ifPresent(comments -> comments.stream().map(comment -> CommentResponse.from(comment)).collect(Collectors.toList()));
		// response.setComments(comments);


		// List<TargetMoneyResponse> targetMoneyResponses = new ArrayList<>();
		// for (TargetMoney tm : funding.getTargetMoneyList()) {
		// 	targetMoneyResponses.add(TargetMoneyResponse.from(tm));
		// }
		//
		// try {
		//
		// 	if (funding.getComments() == null) {
		// 		throw new NotFoundCommentsException();
		// 	}
		//
		// 	if (funding.getHashtags() == null) {
		// 		throw new NotFoundHashtagException();
		// 	}
		//
		//
		// 	List<CommentResponse> commentResponses = new ArrayList<>();
		// 	for (Comment cm : funding.getComments()) {
		// 		commentResponses.add(CommentResponse.from(cm));
		// 	}
		//
		// 	return FundingDetailResponse.builder()
		// 		.team(team)
		// 		.fundingId(funding.getId())
		// 		.category(funding.getCategory().getName())
		// 		.title(funding.getTitle())
		// 		.content(funding.getContent())
		// 		.start(funding.getStartDate())
		// 		.end(funding.getEndDate())
		// 		.postDate(funding.getRegDate())
		// 		.postHashtagList(HashtagResponse.from(funding.getHashtags()))
		// 		.thumbnail(funding.getThumbnail())
		// 		.comments(commentResponses)
		// 		.currentFundingAmount(funding.getCurrentFundingAmount())
		// 		.fundingDescription(funding.getFundingDescription())
		// 		.build();
		//
		// } catch (NotFoundCommentsException e) {
		//
		// 	e.printStackTrace();
		// 	return FundingDetailResponse.builder()
		// 		.team(team)
		// 		.fundingId(funding.getId())
		// 		.category(funding.getCategory().getName())
		// 		.title(funding.getTitle())
		// 		.content(funding.getContent())
		// 		.start(funding.getStartDate())
		// 		.end(funding.getEndDate())
		// 		.postDate(funding.getRegDate())
		// 		.postHashtagList(HashtagResponse.from(funding.getHashtags()))
		// 		.currentFundingAmount(funding.getCurrentFundingAmount())
		// 		.thumbnail(funding.getThumbnail())
		// 		.fundingDescription(funding.getFundingDescription())
		// 		.build();
		//
		// } catch (NotFoundHashtagException e) {
		//
		// 	List<CommentResponse> commentResponses = new ArrayList<>();
		// 	for (Comment cm : funding.getComments()) {
		// 		commentResponses.add(CommentResponse.from(cm));
		// 	}
		//
		// 	return FundingDetailResponse.builder()
		// 		.team(team)
		// 		.fundingId(funding.getId())
		// 		.category(funding.getCategory().getName())
		// 		.title(funding.getTitle())
		// 		.content(funding.getContent())
		// 		.start(funding.getStartDate())
		// 		.end(funding.getEndDate())
		// 		.postDate(funding.getRegDate())
		// 		.thumbnail(funding.getThumbnail())
		// 		.comments(commentResponses)
		// 		.currentFundingAmount(funding.getCurrentFundingAmount())
		// 		.fundingDescription(funding.getFundingDescription())
		// 		.build();
		//
		// }  finally {
		// 	return FundingDetailResponse.builder()
		// 		.team(team)
		// 		.fundingId(funding.getId())
		// 		.category(funding.getCategory().getName())
		// 		.title(funding.getTitle())
		// 		.content(funding.getContent())
		// 		.start(funding.getStartDate())
		// 		.end(funding.getEndDate())
		// 		.postDate(funding.getRegDate())
		// 		.thumbnail(funding.getThumbnail())
		// 		.currentFundingAmount(funding.getCurrentFundingAmount())
		// 		.fundingDescription(funding.getFundingDescription())
		// 		.build();
		// }
	}

}
