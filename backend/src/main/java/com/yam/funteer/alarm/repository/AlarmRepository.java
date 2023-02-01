package com.yam.funteer.alarm.repository;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface AlarmRepository{
	SseEmitter save(String emitterId, SseEmitter sseEmitter);

	void saveEventCache(String eventCacheId, Object event);

	Map<String, SseEmitter> findAllEmitterStartWithById(String userId);

	Map<String, Object> findAllEventCacheStartWithById(String userId);
	void deleteById(String userId);

	void deleteAllEmitterStartWithId(String userId);

	void deleteAllEventCacheStartWithId(String userId);
}