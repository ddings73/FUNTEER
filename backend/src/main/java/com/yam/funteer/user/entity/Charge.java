package com.yam.funteer.user.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "charge")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Charge {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	private Long amount;
	private LocalDateTime chargeDate;


	// 아임포트에서 구분하는 pk
	private String payImpUid;

	// 환불 가능한지 판단용
	private int possibleRefund;

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	private String refundReason;

	public void setPossibleRefund() {
		this.possibleRefund = 0;
	}
	public void setPayImpUid(String payImpUid) {
		this.payImpUid = payImpUid;
	}
}
