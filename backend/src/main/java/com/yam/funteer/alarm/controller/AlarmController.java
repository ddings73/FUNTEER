package com.yam.funteer.alarm.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.yam.funteer.alarm.service.AlarmService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AlarmController {

	private final AlarmService alarmService;

	@GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public ResponseEntity<SseEmitter> subscribe(@RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId){
		return ResponseEntity.ok(alarmService.subscribe(lastEventId));
	}
}
