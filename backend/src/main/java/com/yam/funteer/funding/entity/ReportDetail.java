package com.yam.funteer.funding.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="report_detail")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReportDetail {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public ReportDetail(Report report, String description, Long amount) {
		this.report = report;
		this.description = description;
		this.amount = amount;
	}

	@ManyToOne
	@JoinColumn(name = "report_id")
	private Report report;
	private String description;
	private @NotNull Long amount;

}
