package com.yam.funteer.live.dto;

import io.openvidu.java.client.Recording;
import io.openvidu.java.client.RecordingProperties;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateConnectionRequest {
    private String sessionName;
    private Long fundingId;


    public RecordingProperties toRecordingProperties() {
        return new RecordingProperties.Builder()
            .name(sessionName)
            .outputMode(Recording.OutputMode.COMPOSED)
            .hasAudio(true)
            .hasVideo(true)
            .build();
    }
}
