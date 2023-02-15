package com.yam.funteer.alarm.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.yam.funteer.alarm.service.AlarmService;
import com.yam.funteer.common.BaseResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Api(value="Alarm",tags="알림")
public class AlarmController {

	private final AlarmService alarmService;

	@ApiOperation(value="구독")
	@GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public ResponseEntity<SseEmitter> subscribe(@RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId,
	HttpServletResponse response){
		response.setHeader("Connection", "keep-alive");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("X-Accel-Buffering", "no");

		return ResponseEntity.ok(alarmService.subscribe(lastEventId));
	}

	@ApiOperation(value="읽은 알람 삭제")
	@DeleteMapping("subscribe/alarm/{alarmId}")
	public ResponseEntity<? extends BaseResponseBody> deleteAlarm(@PathVariable Long alarmId){
		alarmService.alarmDelte(alarmId);
		return ResponseEntity.ok(BaseResponseBody.of("success"));
	}

	@ApiOperation(value="유저 알람 전체 삭제")
	@DeleteMapping("subscribe/alarm")
	public ResponseEntity<? extends BaseResponseBody> deleteAlarm(){
		alarmService.alarmAllDelete();
		return ResponseEntity.ok(BaseResponseBody.of("success"));
	}

	@ApiOperation(value="알람 읽기")
	@PutMapping("subscribe/alarm/{alarmId}")
	public ResponseEntity<?> readAlarm(@PathVariable Long alarmId){
		alarmService.alarmRead(alarmId);
		return ResponseEntity.ok(BaseResponseBody.of("success"));
	}

	@ApiOperation(value="알림 가져오기")
	@GetMapping("subscribe/alarm")
	public ResponseEntity<?>  getUserAlarmList(){
		return ResponseEntity.ok(alarmService.getUserAlarmList());
	}

}

