package com.yam.funteer.user.service;

import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.attach.repository.AttachRepository;
import com.yam.funteer.badge.service.BadgeService;
import com.yam.funteer.common.aws.AwsS3Uploader;
import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.common.security.SecurityUtil;
import com.yam.funteer.donation.entity.Donation;
import com.yam.funteer.donation.repository.DonationRepository;
import com.yam.funteer.exception.DuplicateInfoException;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.entity.Report;
import com.yam.funteer.funding.repository.FundingRepository;
import com.yam.funteer.funding.repository.ReportRepository;
import com.yam.funteer.live.entity.Gift;
import com.yam.funteer.live.repository.GiftRepository;
import com.yam.funteer.pay.entity.Payment;
import com.yam.funteer.pay.repository.PaymentRepository;
import com.yam.funteer.user.dto.request.*;
import com.yam.funteer.user.dto.request.member.*;
import com.yam.funteer.user.dto.response.ChargeListResponse;
import com.yam.funteer.user.dto.response.member.GiftDetailResponse;
import com.yam.funteer.user.dto.response.member.MemberAccountResponse;
import com.yam.funteer.user.dto.response.member.MemberProfileResponse;
import com.yam.funteer.user.dto.response.member.MileageDetailResponse;
import com.yam.funteer.user.entity.*;
import com.yam.funteer.user.repository.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Service @Slf4j
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final AttachRepository attachRepository;
    private final ChargeRepository chargeRepository;
    private final AwsS3Uploader awsS3Uploader;
    private final FollowRepository followRepository;
    private final FundingRepository fundingRepository;
    private final DonationRepository donationRepository;
    private final WishRepository wishRepository;
    private final PaymentRepository paymentRepository;
    private final GiftRepository giftRepository;
    private final UserBadgeRepository userBadgeRepository;

    private final BadgeService badgeService;


    @Override
    public void createAccountWithOutProfile(CreateMemberRequest request) {
        memberRepository.findByEmail(request.getEmail()).ifPresent(member -> {
            throw new DuplicateInfoException("이메일이 중복됩니다.");
        });

        request.encryptPassword(passwordEncoder);

        MultipartFile profileImg = request.getProfileImg();
        String filePath = awsS3Uploader.upload(profileImg, "user");
        Attach profile = request.getProfile(filePath);

        Member member = request.toMember(profile);

        attachRepository.save(profile);
        memberRepository.save(member);
        badgeService.initBadges(member);
    }

    @Override
    public void setAccountSignOut(BaseUserRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        String password = request.getPassword()
                .orElseThrow(() -> new IllegalArgumentException("패스워드를 입력해주세요"));

        Member member = memberRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        member.validatePassword(passwordEncoder, password);
        member.signOut();
    }

    @Override
    public MemberProfileResponse getProfile(Long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);


        long followCnt = followRepository.countAllByMemberAndChecked(member, true);
        long wishCnt = wishRepository.countAllByMemberAndChecked(member, true);
        List<UserBadge> userBadgeList = userBadgeRepository.findAllByUserId(member.getId());

        return MemberProfileResponse.of(member, wishCnt, followCnt, userBadgeList);
    }

    @Override
    public void updateProfile(UpdateMemberProfileRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        Member member = validateSameUser(userId, request.getUserId());

        MultipartFile profileImg = request.getProfileImg();

        if(profileImg != null) {
            request.validateProfile();
            String fileName = profileImg.getOriginalFilename();

            String filePath = awsS3Uploader.upload(profileImg, "user");
            Attach profile = member.getProfileImg().orElseGet(() -> request.getProfile(filePath));

            log.info("{} 프로필 이미지 변경", profile.getName());

            if (profile.getId() == null) {
                log.info("새로운 프로필사진 저장");
                attachRepository.save(profile);
                member.updateProfile(profile);
            } else {
                log.info("기존 프로필사진 업데이트 {} ==> {}", profile.getName(), fileName);
                awsS3Uploader.delete(profile.getPath(), "user");
                profile.update(fileName, filePath);
            }
        }

        log.info("{}", member);
        log.info("{}", request);

        if(request.getDisplay() != null) {
            member.updateDisplay(request.getDisplay());
        }
    }

    @Override
    public MemberAccountResponse getAccountInfo() {
        Long userid = SecurityUtil.getCurrentUserId();
        Member member = memberRepository.findById(userid)
            .orElseThrow(UserNotFoundException::new);

        return MemberAccountResponse.of(member);
    }

    @Override
    public void updateAccount(UpdateMemberAccountRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        Member member = validateSameUser(userId, request.getUserId());

        String password = request.getPassword().orElseThrow(()->{
            throw new IllegalArgumentException("패스워드는 필수 입력 값입니다.");
        });
        member.validatePassword(passwordEncoder, password);

        request.getNewPassword().ifPresent(newPw -> {
            String encryptedPw = passwordEncoder.encode(newPw);
            member.changePassword(encryptedPw);
        });
    }

    @Override
    public void followTeam(Long teamId) {
        Long memberId = SecurityUtil.getCurrentUserId();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(UserNotFoundException::new);

        Team team = teamRepository.findById(teamId)
                .orElseThrow(UserNotFoundException::new);

        followRepository.findByMemberAndTeam(member, team)
                .ifPresentOrElse(Follow::toggle, ()->{
                    Follow newFollow = Follow.of(member, team);
                    followRepository.save(newFollow);
                });
    }

    @Override
    public void wishFunding(Long fundingId) {
        Long memberId = SecurityUtil.getCurrentUserId();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(UserNotFoundException::new);

        Funding funding = fundingRepository.findByFundingId(fundingId)
                .orElseThrow(IllegalArgumentException::new);

        wishRepository.findByMemberAndFunding(member, funding)
                .ifPresentOrElse(Wish::toggle, ()->{
                    Wish newWish = Wish.of(member, funding);
                    wishRepository.save(newWish);
                });
    }

    @Override
    public MileageDetailResponse getMileageDetails(PostGroup postGroup, Pageable pageable) {
        Long userId = SecurityUtil.getCurrentUserId();
        Member member = memberRepository.findById(userId)
            .orElseThrow(UserNotFoundException::new);

        Page<Payment> paymentPage = paymentRepository.findAllByUserAndPostPostGroup(member, postGroup, pageable);
        MileageDetailResponse response = MileageDetailResponse.of(paymentPage);
        List<Long> postIdList = response.getPostIdList();

        if(postGroup.equals(PostGroup.FUNDING)){
            List<Funding> fundingList = fundingRepository.findAllById(postIdList);
            response.updateListFromFunding(fundingList);
        }else if(postGroup.equals(PostGroup.DONATION)){
            List<Donation> donationList = donationRepository.findAllById(postIdList);
            response.updateListFromDonation(donationList);
        }

        return response;
    }


    @Override
    public void chargeMileage(ChargeRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();

        Member member = memberRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Long amount = request.getAmount();

        Charge charge = request.toEntity(member);
        chargeRepository.save(charge);
        charge.setPayImpUid(request.getImpUid());
        member.charge(amount);
    }

    @Override
    public Page<ChargeListResponse> getChargeList(Pageable pageable) {
        Long memberId = SecurityUtil.getCurrentUserId();
        Member member = memberRepository.findById(memberId).orElseThrow(UserNotFoundException::new);
        Page<ChargeListResponse> chargeList = chargeRepository.findAllByMember(member, pageable).map(m -> ChargeListResponse.from(m));
        return chargeList;
    }

    @Override
    public GiftDetailResponse getGiftDetails(Pageable pageable) {
        Long memberId = SecurityUtil.getCurrentUserId();
        User user = userRepository.findById(memberId).orElseThrow(UserNotFoundException::new);
        Page<Gift> giftPage = giftRepository.findAllByUser(user, pageable);
        return GiftDetailResponse.of(giftPage);
    }

    private Member validateSameUser(Long i1, Long i2){
        if(i1 != i2)
            throw new IllegalArgumentException("동일 회원만 접근할 수 있습니다");

        return memberRepository.findById(i1).orElseThrow(UserNotFoundException::new);
    }


}