package com.yam.funteer.alarm.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.yam.funteer.post.entity.Alarm;
import com.yam.funteer.post.repository.AlarmRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AlarmServiceImpl implements AlarmService {
	private final AlarmRepository alarmRepository;
	@Override
	public SseEmitter subscribe(Long memberId, String lastEventId) {
		Long emitterId = makeTimeIncludeId(memberId);
		SseEmitter emitter = alarmRepository.save(emitterId, new SseEmitter(timeout));
		emitter.onCompletion(() -> alarmRepository.deleteById(emitterId));
		emitter.onTimeout(() -> alarmRepository.deleteById(emitterId));

		// 503 에러를 방지하기 위한 더미 이벤트 전송
		String eventId = makeTimeIncludeId(memberId);
		sendNotification(emitter, eventId, emitterId, "EventStream Created. [userId=" + memberId + "]");

		// 클라이언트가 미수신한 Event 목록이 존재할 경우 전송하여 Event 유실을 예방
		if (hasLostData(lastEventId)) {
			sendLostData(lastEventId, memberId, emitterId, emitter);
		}

		return emitter;
	}

	private String makeTimeIncludeId(Long memberId) {
		return memberId + "_" + System.currentTimeMillis();
	}

	private void sendNotification(SseEmitter emitter, String eventId, String emitterId, Object data) {
		try {
			emitter.send(SseEmitter.event()
				.id(eventId)
				.data(data));
		} catch (IOException exception) {
			alarmRepository.deleteById(emitterId);
		}
	}

	private boolean hasLostData(String lastEventId) {
		return !lastEventId.isEmpty();
	}

	private void sendLostData(String lastEventId, Long memberId, String emitterId, SseEmitter emitter) {
		Map<String, Object> eventCaches = alarmRepository.findAllEventCacheStartWithByMemberId(String.valueOf(memberId));
		eventCaches.entrySet().stream()
			.filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
			.forEach(entry -> sendNotification(emitter, entry.getKey(), emitterId, entry.getValue()));
	}
}
package com.example.backend.sse;

	import java.io.IOException;
	import lombok.extern.slf4j.Slf4j;
	import org.springframework.http.MediaType;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RestController;
	import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@Slf4j
public class SseController {

	private final SseEmitters sseEmitters;

	public SseController(SseEmitters sseEmitters) {
		this.sseEmitters = sseEmitters;
	}

	@GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public ResponseEntity<SseEmitter> connect() {
		SseEmitter emitter = new SseEmitter();
		sseEmitters.add(emitter);
		try {
			emitter.send(SseEmitter.event()
				.name("connect")
				.data("connected!"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return ResponseEntity.ok(emitter);
	}

	@PostMapping("/count")
	public ResponseEntity<Void> count() {
		sseEmitters.count();
		return ResponseEntity.ok().build();
	}
}