package com.yam.funteer.funding.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.entity.TargetMoney;
import com.yam.funteer.funding.exception.NotFoundCommentsException;
import com.yam.funteer.funding.exception.NotFoundHashtagException;
import com.yam.funteer.post.entity.Comment;
import com.yam.funteer.user.dto.response.team.TeamAccountResponse;
import com.yam.funteer.user.repository.WishRepository;

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

	private List<TargetMoneyResponse> targetMonies;

	private HashtagResponse postHashtagList;

	private String thumbnail;

	private List<CommentResponse> comments;

	private Long currentFundingAmount;

	public static FundingDetailResponse from(Funding funding) {

		WishRepository wishRepository;

		TeamAccountResponse team = TeamAccountResponse.from(funding.getTeam());

		List<TargetMoneyResponse> targetMoneyResponses = new ArrayList<>();
		for (TargetMoney tm : funding.getTargetMoneyList()) {
			targetMoneyResponses.add(TargetMoneyResponse.from(tm));
		}

		try {

			if (funding.getComments() == null) {
				throw new NotFoundCommentsException();
			}

			if (funding.getHashtags() == null) {
				throw new NotFoundHashtagException();
			}


			List<CommentResponse> commentResponses = new ArrayList<>();
			for (Comment cm : funding.getComments()) {
				commentResponses.add(CommentResponse.from(cm));
			}

			return FundingDetailResponse.builder()
				.team(team)
				.fundingId(funding.getId())
				.category(funding.getCategory().getName())
				.title(funding.getTitle())
				.content(funding.getContent())
				.start(funding.getStartDate())
				.end(funding.getEndDate())
				.postDate(funding.getRegDate())
				.targetMonies(targetMoneyResponses)
				.postHashtagList(HashtagResponse.from(funding.getHashtags()))
				.thumbnail(funding.getThumbnail())
				.comments(commentResponses)
				.currentFundingAmount(funding.getCurrentFundingAmount())
				.fundingDescription(funding.getFundingDescription())
				.build();

		} catch (NotFoundCommentsException e) {

			e.printStackTrace();
			return FundingDetailResponse.builder()
				.team(team)
				.fundingId(funding.getId())
				.category(funding.getCategory().getName())
				.title(funding.getTitle())
				.content(funding.getContent())
				.start(funding.getStartDate())
				.end(funding.getEndDate())
				.postDate(funding.getRegDate())
				.targetMonies(targetMoneyResponses)
				.postHashtagList(HashtagResponse.from(funding.getHashtags()))
				.currentFundingAmount(funding.getCurrentFundingAmount())
				.thumbnail(funding.getThumbnail())
				.fundingDescription(funding.getFundingDescription())
				.build();

		} catch (NotFoundHashtagException e) {

			List<CommentResponse> commentResponses = new ArrayList<>();
			for (Comment cm : funding.getComments()) {
				commentResponses.add(CommentResponse.from(cm));
			}

			return FundingDetailResponse.builder()
				// .team(team)
				.fundingId(funding.getId())
				.category(funding.getCategory().getName())
				.title(funding.getTitle())
				.content(funding.getContent())
				.start(funding.getStartDate())
				.end(funding.getEndDate())
				.postDate(funding.getRegDate())
				.targetMonies(targetMoneyResponses)
				.thumbnail(funding.getThumbnail())
				.comments(commentResponses)
				.currentFundingAmount(funding.getCurrentFundingAmount())
				.fundingDescription(funding.getFundingDescription())
				.build();

		}  finally {
			return FundingDetailResponse.builder()
				.team(team)
				.fundingId(funding.getId())
				.category(funding.getCategory().getName())
				.title(funding.getTitle())
				.content(funding.getContent())
				.start(funding.getStartDate())
				.end(funding.getEndDate())
				.postDate(funding.getRegDate())
				.targetMonies(targetMoneyResponses)
				.thumbnail(funding.getThumbnail())
				.currentFundingAmount(funding.getCurrentFundingAmount())
				.fundingDescription(funding.getFundingDescription())
				.build();
		}
	}

}
