package com.yam.funteer.post.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "target_money")
@Builder
public class TargetMoney {

	@EmbeddedId
	private TargetMoneyID targetMoneyID;

	private int amount;
	private String description;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post")
	private Post post;





}
