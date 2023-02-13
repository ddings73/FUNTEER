package com.yam.funteer.pay.service;

import com.yam.funteer.pay.dto.CancelRequest;
import com.yam.funteer.pay.exception.ImpossibleRefundException;

public interface PayService {

	void refundMileage(CancelRequest cancelRequest) throws ImpossibleRefundException;
}
