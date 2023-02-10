package com.yam.funteer.live.controller;

import com.yam.funteer.live.dto.CreateConnectionRequest;
import com.yam.funteer.live.dto.CreateConnectionResponse;
import com.yam.funteer.live.dto.SessionLeaveRequest;
import com.yam.funteer.live.dto.StartRecordingRequest;
import com.yam.funteer.live.service.LiveService;

import io.openvidu.java.client.Recording;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController @Slf4j
@RequestMapping("/openvidu")
@Api(tags = {"라이브"})
@RequiredArgsConstructor
public class LiveController {

    private final LiveService liveService;

    @PostMapping("/sessions")
    public ResponseEntity<CreateConnectionResponse> initializeSession(@RequestBody CreateConnectionRequest request){
        CreateConnectionResponse response = liveService.initializeSession(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/sessions/leave")
    public ResponseEntity leaveSession(@RequestBody SessionLeaveRequest request){

        liveService.leaveSession(request);
        return ResponseEntity.ok("세션떠나기 성공");
    }
}
