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

	// 환불하기 위해서 클라이언트로부터 전달받은 주문번호
	private String payMerchantUid;

	// 환불 가능한지 판단용 number 가능 : 1, 불가 : 그 외 숫자
	private Long possibleRefund;
}
