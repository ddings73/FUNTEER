package com.yam.funteer.funding;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;

import com.yam.funteer.funding.dto.request.FundingRequest;
import com.yam.funteer.funding.repository.FundingRepository;
import com.yam.funteer.funding.service.FundingService;

@SpringBootTest
@WebAppConfiguration
public class FundingTest {

	@Autowired
	private FundingService fundingService;

	@Autowired
	private FundingRepository fundingRepository;


	@Test
	@WithMockUser(roles="USER")
	void createFundingTest() {
		FundingRequest fundingRequest = new FundingRequest();
	}

}
