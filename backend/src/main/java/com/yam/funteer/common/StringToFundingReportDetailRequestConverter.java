package com.yam.funteer.common;

import java.io.IOException;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yam.funteer.funding.dto.request.FundingReportDetailRequest;


public class StringToFundingReportDetailRequestConverter implements Converter<String, FundingReportDetailRequest> {
	@Override
	public FundingReportDetailRequest convert(String value)  {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> map = null;

		try {
			map = objectMapper.readValue(value, Map.class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		return new FundingReportDetailRequest(map.get("amount"), map.get("description"));
	}
}
