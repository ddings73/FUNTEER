package com.yam.funteer.qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.qna.entity.Qna;
import com.yam.funteer.qna.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
	Reply findByQna(Qna qna);
}
