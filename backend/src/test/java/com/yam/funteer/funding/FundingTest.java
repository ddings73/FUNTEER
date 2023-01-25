package com.yam.funteer.funding;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yam.funteer.funding.dto.FundingRequest;
import com.yam.funteer.funding.service.FundingService;
import com.yam.funteer.post.entity.Post;
import com.yam.funteer.post.repository.PostRepository;

@SpringBootTest
public class FundingTest {

	@Autowired
	private FundingService fundingService;

	@Autowired
	private PostRepository postRepository;

	@Test
	void createFundingTest() {

		System.out.println(1);
		Hashtag hashtag1 = new Hashtag("1");
		Hashtag hashtag2 = new Hashtag("2");

		PostHashtag postHashtag = new PostHashtag();
		PostHashtag postHashtag2 = new PostHashtag();

		postHashtag.setHashtag(hashtag1);
		postHashtag2.setHashtag(hashtag2);

		System.out.println(2);
		List<PostHashtag> hashtags = new ArrayList<>();
		hashtags.add(postHashtag);
		hashtags.add(postHashtag2);

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
			.build();

		Post funding = fundingService.createFunding(fundingRequest);
		System.out.println(3);
		Post post = postRepository.findById(funding.getId()).orElseThrow(() -> new IllegalArgumentException());

		System.out.println(funding.getId());
		System.out.println(post.getId());
		Assertions.assertThat(post.getId()).isEqualTo(funding.getId());
	}
}
