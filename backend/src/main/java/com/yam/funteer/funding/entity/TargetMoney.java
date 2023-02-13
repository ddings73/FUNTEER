package com.yam.funteer.funding.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.yam.funteer.common.code.TargetMoneyType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "target_money")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TargetMoney {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "funding_id")
	private Funding funding;
	@Enumerated(value = EnumType.STRING)
	private TargetMoneyType targetMoneyType;
	private @NotNull int amount;

	@OneToMany(mappedBy = "targetMoney",  cascade = CascadeType.ALL)
	private List<TargetMoneyDetail> descriptions;

	public void setTargetMoneyDescriptions(List<TargetMoneyDetail> targetMoneyDetails) {
		this.descriptions = targetMoneyDetails;
	}

	public void setTargetMoneyType(TargetMoneyType targetMoneyType) {
		this.targetMoneyType = targetMoneyType;

	}
}
