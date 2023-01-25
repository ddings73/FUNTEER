package com.yam.funteer.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.post.entity.Post;
import com.yam.funteer.post.entity.Reply;

public interface ReplyRepository extends JpaRepository <Reply,Long>{
	Reply findByPost(Post post);
}
