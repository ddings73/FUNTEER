package com.yam.funteer.post.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(PostId.class)
@Table(name="money")
@NoArgsConstructor
@AllArgsConstructor
public class Money {
	@Id
	@ManyToOne
	@JoinColumn(name="post_id")
	private Post post;

	@GeneratedValue
	@Id
	@Column(name="money_id")
	private Long id;

	@Column(name="money_amount")
	private Integer amount;

	@Column(name="money_description")
	private String description;
}
