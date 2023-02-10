package com.yam.funteer.pay.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CancelRequest {
	private String imp_uid;

	private BigDecimal amount;

	private String reason;

	public boolean isImpUid(){

		if(imp_uid.isEmpty()){
			return false;
		}

		return true;
	}
}
