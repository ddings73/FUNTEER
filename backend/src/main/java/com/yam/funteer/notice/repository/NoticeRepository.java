package com.yam.funteer.notice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yam.funteer.notice.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice,Long> {
	Optional <Notice> findByNoticeId(Long id);
	List<Notice>findAllByOrderByNoticeIdDesc(Pageable pageable);
}
