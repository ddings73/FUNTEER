package com.yam.funteer.live.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateConnectionRequest {
    private String sessionName;
    private Long fundingId;

    public String getSessionName(){
        return "Session_"+sessionName;
    }
}
