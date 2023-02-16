package com.yam.funteer.attach.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.attach.entity.PostAttach;
import com.yam.funteer.post.entity.Post;

public interface PostAttachRepository extends JpaRepository<PostAttach,Long> {
	List<PostAttach> findAllByPost(Post post);
	PostAttach findFirstByPost(Post post);

	PostAttach findByPostId(Long id);
}
