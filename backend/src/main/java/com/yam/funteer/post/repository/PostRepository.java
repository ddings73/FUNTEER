package com.yam.funteer.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yam.funteer.common.code.PostType;
import com.yam.funteer.post.entity.Post;

public interface PostRepository extends JpaRepository<Post,Long> {

	@Query(value = "select * from funding where keyword", nativeQuery = true)
	List<Post> findApprovedFunding(String keyword, String category, String hashTag);

	List<Post>findAllByPostType(PostType postType);

}
