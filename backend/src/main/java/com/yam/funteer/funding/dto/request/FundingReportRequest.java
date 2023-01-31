package com.yam.funteer.funding.dto.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FundingReportRequest {

	private String content;

	private MultipartFile receiptFile;


}
