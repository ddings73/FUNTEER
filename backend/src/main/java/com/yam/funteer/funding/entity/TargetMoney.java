package com.yam.funteer.funding.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.yam.funteer.common.code.TargetMoneyType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "target_money")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TargetMoney {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "funding_id")
	private Funding funding;
	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false)
	private TargetMoneyType targetMoneyType;
	private @NotNull int amount;
	private @NotBlank String description;

	public TargetMoney(Funding funding, TargetMoneyType targetMoneyType, int amount, String description) {
		this.funding = funding;
		this.targetMoneyType = targetMoneyType;
		this.amount = amount;
		this.description = description;
	}
}
