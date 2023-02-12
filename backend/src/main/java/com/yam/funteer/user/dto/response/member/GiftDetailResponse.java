package com.yam.funteer.user.dto.response.member;

import com.yam.funteer.live.entity.Gift;
import lombok.*;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class GiftDetailResponse {

    private List<GiftInfo> giftInfoList;

    public static GiftDetailResponse of(Page<Gift> giftpage){
        List<GiftInfo> giftInfos = giftpage.stream().map(GiftInfo::of).collect(Collectors.toList());
        return new GiftDetailResponse(giftInfos);
    }

    @Builder
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class GiftInfo{
        private String teamName;
        private Long amount;
        private LocalDateTime giftDate;

        public static GiftInfo of(Gift gift){
            return GiftInfo.builder()
                    .amount(gift.getAmount())
                    .giftDate(gift.getGiftDate())
                    .build();
        }
    }
}