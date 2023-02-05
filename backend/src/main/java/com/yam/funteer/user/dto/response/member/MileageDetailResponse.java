package com.yam.funteer.user.dto.response.member;

import com.yam.funteer.pay.entity.Payment;
import com.yam.funteer.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MileageDetailResponse {
    private List<MileageDetail> list;

    public static MileageDetailResponse of(List<Payment> paymentList){
        List<MileageDetail> collect = paymentList.stream().map(MileageDetail::toMileageDetail).collect(Collectors.toList());
        return new MileageDetailResponse(collect);
    }

    @Getter
    @AllArgsConstructor
    private static class MileageDetail{
        private Long postId;
        private String postName;
        private Long amount;
        private LocalDate payDate;

        public static MileageDetail toMileageDetail(Payment payment){
            Post post = payment.getPost();
            LocalDate payDate = payment.getPayDate().toLocalDate();
            return new MileageDetail(post.getId(), post.getTitle(), payment.getAmount(), payDate);
        }
    }
}
