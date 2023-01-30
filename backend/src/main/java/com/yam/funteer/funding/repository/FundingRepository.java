package com.yam.funteer.funding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.common.code.PostType;
import com.yam.funteer.funding.entity.Category;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.post.entity.PostHashtag;

public interface FundingRepository extends JpaRepository<Funding, Long> {
	List<Funding> findAllByPostType(PostType postType);

	List<Funding> findAllByCategory(Category category);

	List<Funding> findByTitleOrContentContaining(String keyword, String keyword2);

	List<Funding> findByHashtags(PostHashtag hashtag);
}
