package com.yam.funteer.live.controller;

import com.yam.funteer.live.dto.*;
import com.yam.funteer.live.service.LiveService;

import io.openvidu.java.client.Recording;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController @Slf4j
@RequestMapping("/openvidu")
@Api(tags = {"라이브"})
@RequiredArgsConstructor
public class LiveController {

    private final LiveService liveService;

    @ApiOperation(value = "라이브 방 생성 or 참여", notes = "단체가 접근하였을 때, 라이브가 생성되지 않았으면 생성함\n 이미 생성된 방에 요청을 보내면 모두 SUBSCRIBER 권한을 가짐")
    @PostMapping("/sessions")
    public ResponseEntity<CreateConnectionResponse> initializeSession(@RequestBody CreateConnectionRequest request){
        CreateConnectionResponse response = liveService.initializeSession(request);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "라이브 방 떠나기", notes = "단체가 방을 떠나면 자동으로 녹화가 저장됨")
    @PostMapping("/sessions/leave")
    public ResponseEntity leaveSession(@RequestBody SessionLeaveRequest request){

        liveService.leaveSession(request);
        return ResponseEntity.ok("세션떠나기 성공");
    }

    @ApiOperation(value = "현재 생성되어 있는 방 목록을 가져옴")
    @GetMapping("/sessions")
    public ResponseEntity<List<String>> getCurrentActiveSessions(){
        List<String> activeSessions = liveService.getCurrentActiveSessions();
        return ResponseEntity.ok(activeSessions);
    }

    @ApiOperation(value = "라이브 후원기능")
    @PostMapping("/sessions/gift")
    public ResponseEntity giftForFundingTeam(@RequestBody GiftRequest request){
        liveService.giftToFundingTeam(request);
        return ResponseEntity.ok("후원이 완료되었습니다.");
    }
}
