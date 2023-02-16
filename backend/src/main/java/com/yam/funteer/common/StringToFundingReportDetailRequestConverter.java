package com.yam.funteer.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yam.funteer.funding.dto.request.FundingReportDetailRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StringToFundingReportDetailRequestConverter implements Converter<String, List<FundingReportDetailRequest>> {
	@Override
	public List<FundingReportDetailRequest> convert(String value)  {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, String>> mapList = null;

		try {
			mapList = objectMapper.readValue(value, List.class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		List<FundingReportDetailRequest> list = new ArrayList<>();
		mapList.forEach(map -> {
			list.add(new FundingReportDetailRequest(map.get("amount"), map.get("description")));
		});

		return list;
	}
}
