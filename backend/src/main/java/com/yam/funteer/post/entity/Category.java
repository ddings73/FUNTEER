package com.yam.funteer.post.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Category {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="category_id")
	private Long id;

	@Column(name="category_name")
	private String name;

	@OneToMany(mappedBy = "category")
	private List<Post>postList=new ArrayList<>();
}
