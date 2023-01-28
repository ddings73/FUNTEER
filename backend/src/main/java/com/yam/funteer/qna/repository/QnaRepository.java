package com.yam.funteer.qna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yam.funteer.common.code.UserType;
import com.yam.funteer.qna.entity.Qna;

public interface QnaRepository extends JpaRepository<Qna,Long> {
	@Query("select q from Qna q where q.user.userType=?1")
	List<Qna>findAllByUserType(UserType userType);

}
