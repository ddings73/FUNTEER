package com.yam.funteer.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.post.entity.Money;
import com.yam.funteer.post.entity.Post;

public interface MoneyReposiroty extends JpaRepository<Money,Long> {
	List<Money>findAllByPost(Post post);
	List<Money>findAllByPostAndId(Post post, Long moneyId);
	Money findFirstByPost(Post post);
}
