package com.yam.funteer.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.post.entity.Post;

public interface PostRepository extends JpaRepository<Post,Long> {
}
