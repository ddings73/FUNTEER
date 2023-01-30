package com.yam.funteer.funding;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.webservices.client.WebServiceClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.test.context.web.WebAppConfiguration;

import com.yam.funteer.funding.dto.FundingRequest;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.repository.FundingRepository;
import com.yam.funteer.funding.service.FundingService;
import com.yam.funteer.post.entity.Hashtag;
import com.yam.funteer.post.entity.Post;
import com.yam.funteer.post.entity.PostHashtag;
import com.yam.funteer.post.repository.PostRepository;


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
