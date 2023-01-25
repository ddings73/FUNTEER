package com.yam.funteer.pay.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.pay.Entity.Payment;
import com.yam.funteer.post.entity.Post;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
	List<Payment> findAllByPost(Post post);
	// List<Payment>findAllByMember(Member member);
	//List<Payment>findAllByTeam(Team team);
}
