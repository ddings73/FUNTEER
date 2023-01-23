package com.yam.funteer.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.code.GroupCode;
import com.yam.funteer.post.entity.Post;

public interface PostRepository extends JpaRepository<Post,Long> {
	List<Post> findAllByGroupCode(GroupCode groupcode);
}
