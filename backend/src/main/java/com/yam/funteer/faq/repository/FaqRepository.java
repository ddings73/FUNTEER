package com.yam.funteer.faq.repository;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.faq.entity.Faq;

public interface FaqRepository extends JpaRepository<Faq,Long> {
	List<Faq>findAllByOrderByFaqIdDesc(Pageable pageable);
	Optional<Faq> findByFaqId(Long faqId);
}
