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

		// Hashtag hashtag1 = new Hashtag("1");
		// Hashtag hashtag2 = new Hashtag("2");
		//
		// PostHashtag postHashtag = new PostHashtag();
		// PostHashtag postHashtag2 = new PostHashtag();
		//
		// postHashtag.setHashtag(hashtag1);
		// postHashtag2.setHashtag(hashtag2);
		//
		// List<PostHashtag> hashtags = new ArrayList<>();
		// hashtags.add(postHashtag);
		// hashtags.add(postHashtag2);

		FundingRequest fundingRequest = FundingRequest.builder()
			.title("test")
			.content("test")
			.startDate(LocalDateTime.now())
			.endDate(LocalDateTime.now())
			.postDate(LocalDateTime.now())
			.amount1(100)
			.description1("test1")
			.amount2(200)
			.description2("test2")
			.amount3(300)
			.description3("test3")
			.hashtags("#1#2#3")
			.build();

		Funding funding = fundingService.createFunding(fundingRequest);

		Funding funding1 = fundingRepository.findById(funding.getId()).orElseThrow(() -> new IllegalArgumentException());

		Assertions.assertThat(funding1.getId()).isEqualTo(funding.getId());
	}
}
