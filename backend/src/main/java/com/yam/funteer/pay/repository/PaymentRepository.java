package com.yam.funteer.pay.repository;

import java.util.List;

import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.pay.entity.Payment;
import com.yam.funteer.post.entity.Post;
import com.yam.funteer.user.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	List<Payment> findAllByPost(Post post);
	List<Payment> findAllByUserAndPostPostGroup(User user,PostGroup postGroup);
	Page<Payment> findAllByUserAndPostPostGroup(User user, PostGroup postGroup, Pageable pageable);

	List<Payment> findAllByUser(User user);

	List<Payment> findByPostId(Long id);
}
