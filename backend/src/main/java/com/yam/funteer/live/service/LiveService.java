package com.yam.funteer.live.service;

import com.yam.funteer.live.dto.*;

import net.minidev.json.JSONObject;

import io.openvidu.java.client.Recording;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LiveService {
	CreateConnectionResponse initializeSession(CreateConnectionRequest sessionName);
    void leaveSession(SessionLeaveRequest request);

    ActiveSessionsResponse getCurrentActiveSessions(Pageable pageable);

    void giftToFundingTeam(GiftRequest request);
}
