package com.yam.funteer.qna.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.qna.entity.Qna;
import com.yam.funteer.user.entity.User;

public interface QnaRepository extends JpaRepository<Qna,Long> {
	List <Qna>findAllByUserOrderByQnaIdDesc(User user, Pageable pageable);
	List<Qna>findAllByOrderByQnaIdDesc(Pageable pageable);
	Optional<Qna>findByQnaId(Long qnaId);

}
