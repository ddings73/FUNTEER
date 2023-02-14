package com.yam.funteer.alarm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.yam.funteer.alarm.entity.AlarmEntity;

@Transactional
public interface AlarmEntityRepository extends JpaRepository<AlarmEntity,Long> {
	List<AlarmEntity> findAllByUserEmailOrderByIdDesc(String userEmail,PageRequest pagerequest);
	void deleteAllByUserEmail(String userEmail);
}
