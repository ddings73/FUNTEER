package com.yam.funteer.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.code.GroupCode;
import com.yam.funteer.post.entity.Category;
import com.yam.funteer.post.entity.Post;

public interface PostRepository extends JpaRepository<Post,Long> {
	List<Post>findAllByCode(GroupCode code);
	List<Post>findAllByCodeAndCategory(GroupCode code, Category category);
	List<Post>findAllByCodeAndCode(GroupCode postCode, GroupCode memberCode);

}
