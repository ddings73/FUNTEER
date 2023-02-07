package com.yam.funteer.live.controller;

import com.yam.funteer.live.dto.CreateConnectionRequest;
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
    public ResponseEntity<JSONObject> initializeSession(@RequestBody CreateConnectionRequest request){
        JSONObject response = liveService.initializeSession(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/sessions/leave")
    public ResponseEntity leaveSession(@RequestBody Map<String, String> params){
        String sessionName = params.get("sessionName");
        String token = params.get("token");

        liveService.leaveSession(sessionName, token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/recording/start")
    public ResponseEntity startRecording(@RequestBody StartRecordingRequest request){
        Recording recording = liveService.startRecording(request);
        return ResponseEntity.ok(recording);
    }

    @PostMapping("/recording/stop/{recordingId}")
    public ResponseEntity stopRecording(@PathVariable String recordingId){
        Recording recording = liveService.stopRecording(recordingId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/recording/{recordingId}")
    public ResponseEntity<Recording> getRecording(@PathVariable String recordingId){
        Recording recording = liveService.getRecording(recordingId);
        return ResponseEntity.ok(recording);
    }
}
