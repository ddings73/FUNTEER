package com.yam.funteer.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.post.entity.Category;
import com.yam.funteer.post.entity.Post;

public interface CategoryRepository extends JpaRepository<Category,Long> {
	List<Post>findAllByName(String category);
}
