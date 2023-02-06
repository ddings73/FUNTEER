package com.yam.funteer.funding.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TargetMoneyDetail {

	@Id @GeneratedValue
	private Long id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "target_money_id")
	private TargetMoney targetMoney;
	private String description;
	public TargetMoneyDetail(TargetMoney targetMoney, String description) {
		this.targetMoney = targetMoney;
		this.description = description;
	}

}
