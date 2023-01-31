package com.yam.funteer.post.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.yam.funteer.post.entity.PostHashtag;


public interface PostHashtagRepository extends JpaRepository<PostHashtag, Long> {

	List<PostHashtag> findByHashtagId(Long hashtagId);

}
