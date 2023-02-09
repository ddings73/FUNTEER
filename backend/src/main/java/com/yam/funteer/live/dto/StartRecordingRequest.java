package com.yam.funteer.live.dto;

import io.openvidu.java.client.Recording;
import io.openvidu.java.client.RecordingProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class StartRecordingRequest {
	private String sessionId;
	private boolean hasAudio;
	private boolean hasVideo;

	public RecordingProperties toRecordingProperties() {
		return new RecordingProperties.Builder()
			.outputMode(Recording.OutputMode.INDIVIDUAL)
			.hasAudio(hasAudio)
			.hasVideo(hasVideo)
			.build();
	}
}
