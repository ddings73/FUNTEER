package com.yam.funteer.user.dto.response.team;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.yam.funteer.live.entity.Gift;
import org.springframework.data.domain.Page;

import com.yam.funteer.pay.entity.Payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamGiftDetailResponse {

	private long totalElements;
	private int totalPages;
	private List<GiftInfo> giftList;

	public static TeamGiftDetailResponse of(Page<Gift> giftPage) {
		List<GiftInfo> collect = giftPage.stream().map(GiftInfo::of).collect(Collectors.toList());
		return new TeamGiftDetailResponse(giftPage.getTotalElements(), giftPage.getTotalPages(), collect);
	}

	@Builder
	@Getter @Setter
	@NoArgsConstructor
	@AllArgsConstructor
	private static class GiftInfo {
		private Long giftId;
		private String fundTitle;
		private String username;
		private Long amount;
		private LocalDate giftDate;

		public static GiftInfo of(Gift gift){
			return GiftInfo.builder()
				.giftId(gift.getId())
				.fundTitle(gift.getLive().getFunding().getTitle())
				.username(gift.getUser().getName())
				.amount(gift.getAmount())
				.giftDate(gift.getGiftDate().toLocalDate())
				.build();
		}

	}
}
