package com.yam.funteer.donation.dto.request;

import com.yam.funteer.common.code.PostType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DonationStatusModify {
	private Long donationId;
	private PostType postType;
}
