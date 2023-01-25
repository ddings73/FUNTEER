package com.yam.funteer.post.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.yam.funteer.post.TargetMoneyType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode
public class TargetMoneyID implements Serializable {

	@Column(name="target_money_type")
	private TargetMoneyType targetMoneyType;

	@Column(name="post_id")
	private Long postId;
}
