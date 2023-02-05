package com.yam.funteer.pay.dto;

import java.math.BigDecimal;

import com.siot.IamportRestClient.request.ExtraRequesterEntry;

import lombok.Data;

@Data
public class CancelRequest {

	private String imp_uid;

	private String merchant_uid;

	private BigDecimal amount;

	private BigDecimal tax_free;

	private BigDecimal checksum;

	private String reason;

	private String refund_holder;

	private String refund_bank;

	private String refund_account;

	private boolean escrow_confirmed;

	private ExtraRequesterEntry extra;

	public boolean isImpUid(){

		if(imp_uid.isEmpty()){
			return false;
		}

		return true;
	}
}
