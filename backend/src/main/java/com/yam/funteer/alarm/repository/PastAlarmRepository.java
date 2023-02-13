package com.yam.funteer.alarm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.alarm.entity.Alarm;
import com.yam.funteer.alarm.entity.PastAlarm;
import com.yam.funteer.user.entity.User;

public interface PastAlarmRepository extends JpaRepository<PastAlarm,Long> {
	List<PastAlarm> findAllByUserUserEmail(String email);
	Optional<PastAlarm> findByUserAndAlarmId(User user,Long alarmId);
}
