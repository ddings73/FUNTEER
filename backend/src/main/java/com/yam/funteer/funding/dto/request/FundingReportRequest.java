package com.yam.funteer.funding.dto.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class FundingReportRequest {

	private String content;

	private MultipartFile receiptFile;

	private List<FundingReportDetailRequest> fundingDetailRequests;


}
