package com.yam.funteer.live.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSessionRequest {
    private String sessionName;
    private Long fundingId;
}
