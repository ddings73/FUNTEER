package com.yam.funteer.post.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.post.entity.Hashtag;

public interface HashTagRepository extends JpaRepository<Hashtag, Long> {
	Optional<Hashtag> findOneByName(String name);

	Optional<Hashtag> findByName(String hashtag);
}
