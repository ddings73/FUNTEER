package com.yam.funteer.post.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.funding.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
	Optional<Category> findByName(String categoryName);
}
