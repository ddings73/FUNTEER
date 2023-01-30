package com.yam.funteer.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.post.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
