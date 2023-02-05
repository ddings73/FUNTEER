package com.yam.funteer.live.controller;

import com.yam.funteer.live.dto.CreateSessionRequest;
import com.yam.funteer.live.service.LiveService;
import io.openvidu.java.client.*;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController @Slf4j
@RequestMapping("/openvidu")
@Api(tags = {"라이브"})
@RequiredArgsConstructor
public class LiveController {

    private final LiveService liveService;

    @PostMapping("/sessions")
    public ResponseEntity<JSONObject> initializeSession(@RequestBody CreateSessionRequest request) throws OpenViduJavaClientException, OpenViduHttpException {
        JSONObject response = liveService.initializeSession(request);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/sessions/{sessionId}/connections")
    public ResponseEntity<String> createConnection(@PathVariable String sessionId,
                                                   @RequestBody(required = false) Map<String, Object> params) throws OpenViduJavaClientException, OpenViduHttpException {
//        Session session = openVidu.getActiveSession(sessionId);
//        if(session == null){
//            return ResponseEntity.notFound().build();
//        }
//
//        ConnectionProperties properties = ConnectionProperties.fromJson(params).build();
//        Connection connection = session.createConnection(properties);
//        return ResponseEntity.ok(connection.getToken());
        return null;
    }

    @PostMapping("/sessions/leave")
    public ResponseEntity leaveSession(@RequestBody Map<String, String> params){
        String sessionName = params.get("sessionName");
        String token = params.get("token");

        liveService.leaveSession(sessionName, token);
        return ResponseEntity.ok().build();
    }
}
