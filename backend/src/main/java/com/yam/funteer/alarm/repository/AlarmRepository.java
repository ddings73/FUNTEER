package com.yam.funteer.alarm.repository;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface AlarmRepository{
	SseEmitter save(String emitterId, SseEmitter sseEmitter); //Emitter 저장

	void saveEventCache(String eventCacheId, Object event); //이벤트 저장

	Map<String, SseEmitter> findAllEmitterStartWithById(String userId); //해당 회원과  관련된 모든 Emitter를 찾는다

	Map<String, SseEmitter> findAllEmitterStartWithByUserIdInList(List userIds); //List 에서 해당 회원과  관련된 모든 Emitter를 찾는다(미 개발)

	Map<String, Object> findAllEventCacheStartWithById(String userId); //해당 회원과관련된 모든 이벤트를 찾는다

	void deleteById(String userId); //Emitter를 지운다

	void deleteAllEmitterStartWithId(String userId); //해당 회원과 관련된 모든 Emitter를 지운다

	void deleteAllEventCacheStartWithId(String userId); //해당 회원과 관련된 모든 이벤트를 지운다
}