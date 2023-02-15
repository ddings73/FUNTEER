package com.yam.funteer.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yam.funteer.funding.dto.request.FundingReportDetailRequest;


public class StringToFundingReportDetailRequestConverter implements Converter<String, List<FundingReportDetailRequest>> {
	@Override
	public List<FundingReportDetailRequest> convert(String value)  {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> map = null;

		try {
			map = objectMapper.readValue(value, Map.class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		List<FundingReportDetailRequest> list = new ArrayList<>();
		map.forEach((k, v) ->{
			list.add(new FundingReportDetailRequest(k, v));
		});

		return list;
	}
}
