package com.yam.funteer.funding.dto;

import com.yam.funteer.user.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TakeFundingRequest {

	Long amount;

}
