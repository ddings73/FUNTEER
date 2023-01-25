package com.yam.funteer.post.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.yam.funteer.post.entity.Hashtag;
import com.yam.funteer.post.entity.Post;
import com.yam.funteer.post.entity.PostHashtag;
import com.yam.funteer.post.entity.PostHashtagId;


public interface PostHashtagRepository extends CrudRepository<PostHashtag, PostHashtagId> {
	List<Hashtag>findPostHashtagsByPost(Post post);
	List<Post>findPostHashtagsByHashtag(Hashtag hashtag);

	@Transactional
	List<PostHashtag>deleteAllByPost(Post post);
}
