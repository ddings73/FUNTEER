package com.yam.funteer.alarm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.alarm.entity.AlarmEntity;

public interface AlarmEntityRepository extends JpaRepository<AlarmEntity,String> {

	List<AlarmEntity> findAllByUserEmail(String userEmail);
	void deleteAllByUserEmail(String userEmail);
}
