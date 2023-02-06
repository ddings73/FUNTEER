package com.yam.funteer.live.service;

import com.yam.funteer.live.dto.CreateConnectionRequest;
import com.yam.funteer.live.dto.StartRecordingRequest;

import net.minidev.json.JSONObject;

import io.openvidu.java.client.Recording;

public interface LiveService {
    JSONObject initializeSession(CreateConnectionRequest sessionName);

    void leaveSession(String sessionName, String token);

	Recording startRecording(StartRecordingRequest request);

	Recording getRecording(String recordingId);

	Recording stopRecording(String recordingId);
}
