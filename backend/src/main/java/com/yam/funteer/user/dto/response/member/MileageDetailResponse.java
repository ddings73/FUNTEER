package com.yam.funteer.user.dto.response.member;

import com.yam.funteer.donation.entity.Donation;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.entity.Report;
import com.yam.funteer.pay.entity.Payment;
import com.yam.funteer.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MileageDetailResponse {
    private long totalElements;
    private int totalPages;
    private List<MileageDetail> list;

    public static MileageDetailResponse of(Page<Payment> paymentPage){
        List<MileageDetail> collect = paymentPage.stream().map(MileageDetail::toMileageDetail).collect(Collectors.toList());
        return new MileageDetailResponse(paymentPage.getTotalElements(), paymentPage.getTotalPages(), collect);
    }

    public List<Long> getPostIdList() {
        return this.list.stream().map(MileageDetail::getPostId).collect(Collectors.toList());
    }

    public void updateListFromFunding(List<Funding> fundingList) {
        list.forEach(mileageDetail -> {
            Long postId = mileageDetail.getPostId();
            Funding findFunding = fundingList.stream().filter(funding -> funding.getId() == postId).findFirst().get();
            mileageDetail.setPostId(findFunding.getFundingId());
            mileageDetail.setThumbnail(findFunding.getThumbnail());
        });
    }

    public void updateListFromDonation(List<Donation> donationList) {
        list.forEach(mileageDetail -> {
            Long postId = mileageDetail.getPostId();
            Donation findDonation = donationList.stream().filter(donation -> donation.getId() == postId).findFirst().get();
            mileageDetail.setPostId(findDonation.getDonationId());
        });
    }

    @Getter @Setter
    @AllArgsConstructor
    private static class MileageDetail{

        private String thumbnail;
        private Long postId;
        private String postName;
        private Long amount;
        private LocalDate payDate;

        public static MileageDetail toMileageDetail(Payment payment){
            Post post = payment.getPost();

            LocalDate payDate = payment.getPayDate().toLocalDate();
            return new MileageDetail(post.getThumbnail(), post.getId(), post.getTitle(), payment.getAmount(), payDate);
        }
    }
}
