package com.yam.funteer.qna.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yam.funteer.common.code.UserType;
import com.yam.funteer.qna.entity.Qna;
import com.yam.funteer.user.entity.User;

public interface QnaRepository extends JpaRepository<Qna,Long> {
	List <Qna>findAllByUser(User user);

}
