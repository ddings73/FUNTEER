package com.yam.funteer.pay.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.pay.Entity.Gift;
import com.yam.funteer.post.entity.Post;

public interface GiftRepository extends JpaRepository<Gift,Long> {
	List<Gift>findAllByPost(Post post);
	// List<Gift>findAllByMember(Member member);
	//List<Gift>findAllByTeam(Team team);
}
