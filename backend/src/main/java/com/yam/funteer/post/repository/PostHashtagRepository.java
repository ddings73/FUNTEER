package com.yam.funteer.post.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.post.entity.Hashtag;
import com.yam.funteer.post.entity.Post;
import com.yam.funteer.post.entity.PostHashtag;

import io.swagger.models.Path;

public interface PostHashtagRepository extends JpaRepository<PostHashtag, Long> {

	List<PostHashtag> findByHashtag(Long hashtagId);

	PostHashtag findOneByHashtag(Optional<Hashtag> oneByName);

	List<PostHashtag> findByHashtagId(Long hashtagId);

	List<PostHashtag> findByHashtagIdContaining(Long hashtagId);
}
