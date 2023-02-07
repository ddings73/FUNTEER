package com.yam.funteer.alarm.repository;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface AlarmRepository{
	SseEmitter save(String emitterId, SseEmitter sseEmitter);

	void saveEventCache(String eventCacheId, Object event);

	Map<String, SseEmitter> findAllEmitterStartWithByEmail(String email);

	Map<String, Object> findAllEventCacheStartWithByEmail(String email);

	void deleteById(String id);

	void deleteAllEmitterStartWithId(String email);

	void deleteAllEventCacheStartWithId(String email);
}