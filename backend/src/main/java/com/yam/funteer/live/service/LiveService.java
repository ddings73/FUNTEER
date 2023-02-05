package com.yam.funteer.live.service;

import com.yam.funteer.live.dto.CreateSessionRequest;
import net.minidev.json.JSONObject;

public interface LiveService {
    JSONObject initializeSession(CreateSessionRequest sessionName);

    void leaveSession(String sessionName, String token);
}
