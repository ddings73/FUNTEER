package com.yam.funteer.funding.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.yam.funteer.funding.entity.TargetMoney;
import com.yam.funteer.post.entity.Hashtag;
import com.yam.funteer.post.entity.PostHashtag;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HashtagResponse {

	private List<String> name;

	public static HashtagResponse from(List<PostHashtag> postHashtags) {
		return new HashtagResponse(postHashtags.stream().map(postHashtag ->
			postHashtag.getHashtag().getName()).collect(Collectors.toList()));
	}
}
