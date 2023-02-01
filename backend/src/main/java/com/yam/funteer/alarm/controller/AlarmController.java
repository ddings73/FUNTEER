package com.yam.funteer.alarm.controller;
import java.io.IOException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.yam.funteer.alarm.service.AlarmService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AlarmController {

	private final AlarmService alarmService;

	@GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public ResponseEntity<SseEmitter> connect() {
		SseEmitter emitter = new SseEmitter();
		alarmService.add(emitter);
		try {
			emitter.send(SseEmitter.event()
				.name("connect")
				.data("connected!"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return ResponseEntity.ok(emitter);
	}
}