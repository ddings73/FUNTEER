package com.yam.funteer.funding.dto;

import com.yam.funteer.user.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FundingCommentRequest {
	
	private String content;
}
