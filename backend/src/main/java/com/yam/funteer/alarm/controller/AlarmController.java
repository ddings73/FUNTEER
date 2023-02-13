package com.yam.funteer.alarm.controller;

import javax.transaction.Transactional;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.yam.funteer.alarm.service.AlarmService;
import com.yam.funteer.common.BaseResponseBody;

import lombok.RequiredArgsConstructor;

@RestController
@Transactional
@RequiredArgsConstructor
public class AlarmController {

	private final AlarmService alarmService;

	@GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public SseEmitter subscribe(@RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId){
		return alarmService.subscribe(lastEventId);
	}

	@DeleteMapping("subscribe/alarm")
	public ResponseEntity<? extends BaseResponseBody> deleteAlarm(){
		alarmService.alarmRead();
		return ResponseEntity.ok(BaseResponseBody.of("success"));
	}

	@GetMapping("subscribe/alarm")
	public ResponseEntity<?>  getPastAlarmList(){
		return ResponseEntity.ok(alarmService.getPastAlarmList());
	}
}
