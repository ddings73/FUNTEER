package com.yam.funteer.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.user.entity.Member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wish")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wish {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "funding_id")
	private Funding funding;
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	private Boolean checked;

	private Wish(Member member, Funding funding){
		this.member = member;
		this.funding = funding;
		this.checked = true;
	}
	public static Wish of(Member member, Funding funding) {
		return new Wish(member, funding);
	}

	public void toggle() {
		this.checked = !this.checked;
	}

	public void doIt() {
		this.checked = true;
	}
}
