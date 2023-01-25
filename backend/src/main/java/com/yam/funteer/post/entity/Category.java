package com.yam.funteer.post.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="category")
public class Category {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="category_id")
	private Long id;

	@Column(name="category_name")
	private String name;

	// @OneToMany(targetEntity = Post.class)
	// @JoinColumn(name="post_id")
	// private List<Post> postList=new ArrayList<>();

}
