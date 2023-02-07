package com.yam.funteer.live.service;

import com.yam.funteer.live.dto.CreateConnectionRequest;
import com.yam.funteer.live.dto.CreateConnectionResponse;
import com.yam.funteer.live.dto.SessionLeaveRequest;
import com.yam.funteer.live.dto.StartRecordingRequest;

import net.minidev.json.JSONObject;

import io.openvidu.java.client.Recording;

public interface LiveService {
	CreateConnectionResponse initializeSession(CreateConnectionRequest sessionName);
    void leaveSession(SessionLeaveRequest request);

}
