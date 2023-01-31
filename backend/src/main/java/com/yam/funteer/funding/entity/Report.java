package com.yam.funteer.funding.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.common.code.PostType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "report")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Report{
	public Report(Funding funding, String content, LocalDateTime regDate, Attach receipts) {
		this.funding = funding;
		this.content = content;
		this.regDate = regDate;
		this.receipts = receipts;
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@JoinColumn(name = "funding_id")
	private Funding funding;
	private String content;
	private LocalDateTime regDate;

	private String rejectReason;

	@OneToOne
	@JoinColumn(name = "recipt_id")
	private Attach receipts;

	@OneToMany(mappedBy = "report")
	private List<ReportDetail> reportDetails;

	public void setRejectComment(String rejectReason) {
		this.rejectReason = rejectReason;
	}
}
