package com.yam.funteer.post.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.post.entity.Hashtag;

public interface HashtagRepository extends JpaRepository<Hashtag,Long> {
	Optional<Hashtag> findByName(String hashtagName);
}
