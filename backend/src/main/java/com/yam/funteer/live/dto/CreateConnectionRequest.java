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

    private boolean hasAudio;
    private boolean hasVideo;

    public RecordingProperties toRecordingProperties() {
        return new RecordingProperties.Builder()
            .name(sessionName)
            .outputMode(Recording.OutputMode.INDIVIDUAL)
            .hasAudio(hasAudio)
            .hasVideo(hasVideo)
            .build();
    }
}
