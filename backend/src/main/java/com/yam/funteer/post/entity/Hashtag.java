package com.yam.funteer.post.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="hashtag")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Hashtag {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String name;

	@OneToMany(mappedBy = "hashtag")
	private List<PostHashtag> postHashtagList=new ArrayList<>();


	public Hashtag(String name) {
		this.name = name;
	}
}
