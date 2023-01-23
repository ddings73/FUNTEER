package com.yam.funteer.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.yam.funteer.post.entity.Hashtag;
import com.yam.funteer.post.entity.PostHashtag;
import com.yam.funteer.post.entity.PostHashtagId;

import lombok.Getter;
import lombok.Setter;

public interface PostHashtagRepository extends CrudRepository<PostHashtag, PostHashtagId> {

}
