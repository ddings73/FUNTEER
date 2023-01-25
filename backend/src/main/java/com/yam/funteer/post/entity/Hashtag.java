package com.yam.funteer.post.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="hashtag")
public class Hashtag {
	@Id
	@Column(name="hashtag_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="hashtag_name")
	private String name;

	@OneToMany(mappedBy = "hashtag")
	private List<PostHashtag>postHashtagList=new ArrayList<>();

	public Hashtag() {
	}
	public Hashtag(String name) {
		this.name = name;
	}

}
