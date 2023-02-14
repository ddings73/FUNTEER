package com.yam.funteer.live.dto;

import com.yam.funteer.live.entity.Live;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ActiveSessionsResponse {

    private List<SessionInfo> sessionInfoList;
    private long totalElements;
    private int totalPages;

    public static ActiveSessionsResponse of(Page<Live> livePage) {
        List<SessionInfo> collect = livePage.stream().map(SessionInfo::of).collect(Collectors.toList());
        return new ActiveSessionsResponse(collect, livePage.getTotalElements(), livePage.getTotalPages());
    }

    @Getter @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class SessionInfo{
        private String sessionName;
        private String thumbnail;

        public static SessionInfo of(Live live){
            return SessionInfo.builder()
                    .sessionName(live.getTeam().getName())
                    .thumbnail(live.getFunding().getThumbnail())
                    .build();
        }
    }
}
