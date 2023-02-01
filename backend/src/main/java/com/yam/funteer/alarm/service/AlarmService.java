package com.yam.funteer.alarm.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.yam.funteer.alarm.dto.response.AlarmRes;
import com.yam.funteer.common.security.SecurityUtil;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.alarm.entity.Alarm;
import com.yam.funteer.post.entity.Post;
import com.yam.funteer.alarm.repository.AlarmRepository;
import com.yam.funteer.user.entity.User;
import com.yam.funteer.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlarmService {

	private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

	private final AlarmRepository alarmRepository;
	private final UserRepository userRepository;

	//로그인 한 유저 서비스 등록
	public SseEmitter subscribe( String lastEventId) {
		User user= userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());

		//유실 시 찾기 위해서 유저 아이디 + 시간
		String id = user.getId() + "_" + System.currentTimeMillis();

		SseEmitter emitter = alarmRepository.save(id, new SseEmitter(DEFAULT_TIMEOUT));

		emitter.onCompletion(() -> alarmRepository.deleteById(id));
		emitter.onTimeout(() -> alarmRepository.deleteById(id));

		// 503 에러를 방지하기 위한 더미 이벤트 전송
		sendToClient(emitter, id, "EventStream Created. [userId=" + user.getId() + "]");

		// 클라이언트가 미수신한 Event 목록이 존재할 경우 전송하여 Event 유실을 예방
		if (!lastEventId.isEmpty()) {
			Map<String, Object> events = alarmRepository.findAllEventCacheStartWithById(String.valueOf(user.getId()));
			events.entrySet().stream()
				.filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
				.forEach(entry -> sendToClient(emitter, entry.getKey(), entry.getValue()));
		}

		return emitter;
	}

	//개인 알림
	//reply
	//funding 상세 변경
	public void send(User user,String title , String content,Post post) {
		Alarm alarm = createNotification(user, title, content,post);

		// 로그인 한 유저의 SseEmitter 모두 가져오기
		Map<String, SseEmitter> sseEmitters = alarmRepository.findAllEmitterStartWithById(String.valueOf(user.getId()));
		sseEmitters.forEach(
			(key, emitter) -> {
				// 데이터 캐시 저장(유실된 데이터 처리하기 위함)
				alarmRepository.saveEventCache(key, alarm);
				// 데이터 전송
				sendToClient(emitter, key,new AlarmRes(alarm));
			}
		);
	}

	//list에 있는 유저에게 알림 전송(funding)
	//팔로잉 하는 단체의 펀딩 생성
	//펀딩 참여자 or 찜하기 한 펀딩 상태 변경 시
	public void sendList(List userList, String title, String content, Post post) {
		List<Alarm> notifications = new ArrayList<>();
		Map<String, SseEmitter> sseEmitters;

		for (int i = 0; i < userList.size(); i++) {
			int num = i;
			sseEmitters = new HashMap<>();

			notifications.add(createNotification((User)userList.get(i),title,content,post));
			sseEmitters.putAll(alarmRepository.findAllEmitterStartWithById(((User)userList.get(i)).getId().toString()));

			sseEmitters.forEach(
				(key, emitter) -> {
					// 데이터 캐시 저장(유실된 데이터 처리하기 위함)
					alarmRepository.saveEventCache(key, notifications.get(num));
					// 데이터 전송
					sendToClient(emitter, key, notifications.get(num));
				}
			);
		}
	}

	//알림 생성
	private Alarm createNotification(User user, String title, String content,Post post) {
		return Alarm.builder()
			.user(user)
			.content(content)
			.title(title)
			.checked(false)
			.date(LocalDateTime.now())
			.post(post).build();
	}

	//알림 전송
	private void sendToClient(SseEmitter emitter, String id, Object data) {
		try {
			emitter.send(SseEmitter.event()
				.id(id)
				.name("sse")
				.data(data, MediaType.APPLICATION_JSON)
				.reconnectTime(0));

			emitter.complete();

			alarmRepository.deleteById(id);
		} catch (IOException exception) {
			alarmRepository.deleteById(id);
			throw new RuntimeException("연결 오류!");
		}
	}
}
