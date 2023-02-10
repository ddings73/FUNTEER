package com.yam.funteer.user.dto.response.team;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.yam.funteer.pay.entity.Payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamPaymentReceiptResponse {

	private List<PaymentInfo>  payList;

	public static TeamPaymentReceiptResponse of(Page<Payment> findPayments) {
		List<PaymentInfo> collect = findPayments.stream().map(PaymentInfo::of).collect(Collectors.toList());
		return new TeamPaymentReceiptResponse(collect);
	}

	@Builder
	@Getter @Setter
	@NoArgsConstructor
	@AllArgsConstructor
	private static class PaymentInfo{
		private String userName;
		private Long amount;
		private LocalDate payDate;

		public static PaymentInfo of(Payment payment){
			return PaymentInfo.builder()
				.userName(payment.getUser().getName())
				.amount(payment.getAmount())
				.payDate(payment.getPayDate().toLocalDate())
				.build();
		}

	}
}
