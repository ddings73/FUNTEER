package com.yam.funteer.alarm.repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@NoArgsConstructor
@Slf4j
public class AlarmRepositoryImpl implements AlarmRepository {
	private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
	private final Map<String, Object> eventCache = new ConcurrentHashMap<>();

	@Override
	public SseEmitter save(String emitterId, SseEmitter sseEmitter) {
		emitters.put(emitterId, sseEmitter);
		log.info(String.valueOf(emitters));
		return sseEmitter;
	}

	@Override
	public void saveEventCache(String eventCacheId, Object event) {
		eventCache.put(eventCacheId, event);
	}

	@Override
	public Map<String, SseEmitter> findAllEmitterStartWithByEmail(String email) {
		return emitters.entrySet().stream() //여러개의 Emitter가 존재할 수 있기떄문에 stream 사용
			.filter(entry -> entry.getKey().startsWith(email))
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	@Override
	public Map<String, Object> findAllEventCacheStartWithByEmail(String email) {
		return emitters.entrySet().stream()
			.filter(entry -> entry.getKey().startsWith(email))
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	@Override
	public void deleteById(String id) {
		emitters.remove(id);
	}

	@Override
	public void deleteAllEmitterStartWithId(String userId) {
		emitters.forEach((key, emitter) -> {
			if (key.startsWith(userId)){
				emitters.remove(key);
			}
		});
	}

	@Override
	public void deleteAllEventCacheStartWithId(String userId) {
		emitters.forEach((key, emitter) -> {
			if (key.startsWith(userId)){
				emitters.remove(key);
			}
		});
	}
}

