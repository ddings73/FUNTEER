package com.yam.funteer.pay.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import com.yam.funteer.pay.dto.CancelRequest;
import com.yam.funteer.pay.exception.ImpossibleRefundException;
import com.yam.funteer.pay.service.PayService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Api(tags ={"충전"})
public class PayController {

	private IamportClient iamportClient;
	private final PayService payService;

	@Value("${iamport.key}")
	private String impKey;
	@Value("${iamport.secret}")
	private String impSecret;

	@PostMapping("/verifyIamport/{imp_uid}")
	public IamportResponse<Payment> paymentByImpUid(@PathVariable String imp_uid) throws IamportResponseException, IOException {
		iamportClient = new IamportClient(impKey, impSecret);
		return iamportClient.paymentByImpUid(imp_uid);
	}

	@PostMapping("/cancelIamport")
	public IamportResponse<Payment> cancelByImpUid(@RequestBody CancelRequest cancelRequest) throws
		IamportResponseException,
		IOException,
		ImpossibleRefundException {

		CancelData cancelData = new CancelData(cancelRequest.getImp_uid(), cancelRequest.isImpUid(),
			cancelRequest.getAmount());

		iamportClient = new IamportClient(impKey, impSecret);

		payService.refundMileage(cancelRequest);

		return iamportClient.cancelPaymentByImpUid(cancelData);
	}
}
