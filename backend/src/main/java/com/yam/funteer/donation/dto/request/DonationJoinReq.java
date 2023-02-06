package com.yam.funteer.donation.dto.request;
import javax.validation.constraints.NotBlank;

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
public class DonationJoinReq {
	@NotBlank
	private String paymentAmount;

}
