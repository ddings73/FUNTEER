package com.yam.funteer.user.service;

import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.attach.repository.AttachRepository;
import com.yam.funteer.badge.repository.BadgeRepository;
import com.yam.funteer.badge.service.BadgeService;
import com.yam.funteer.common.aws.AwsS3Uploader;
import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.common.security.SecurityUtil;
import com.yam.funteer.exception.DuplicateInfoException;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.repository.FundingRepository;
import com.yam.funteer.pay.entity.Payment;
import com.yam.funteer.pay.repository.PaymentRepository;
import com.yam.funteer.user.dto.request.*;
import com.yam.funteer.user.dto.request.member.*;
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
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final AttachRepository attachRepository;
    private final ChargeRepository chargeRepository;
    private final AwsS3Uploader awsS3Uploader;
    private final FollowRepository followRepository;
    private final FundingRepository fundingRepository;
    private final WishRepository wishRepository;
    private final PaymentRepository paymentRepository;
    private final UserBadgeRepository userBadgeRepository;

    private final BadgeService badgeService;


    @Override
    public void createAccountWithOutProfile(CreateMemberRequest request) {
        memberRepository.findByEmail(request.getEmail()).ifPresent(member -> {
            throw new DuplicateInfoException("이메일이 중복됩니다.");
        });

        request.encryptPassword(passwordEncoder);
        Member member = request.toMember();
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

        member.validate();

        long followCnt = followRepository.countAllByMember(member);
        long wishCnt = wishRepository.countAllByMember(member);
        List<UserBadge> userBadgeList = userBadgeRepository.findAllByUserId(member.getId());

        return MemberProfileResponse.of(member, wishCnt, followCnt, userBadgeList);
    }

    @Override
    public void updateProfile(UpdateMemberProfileRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        Member member = validateSameUser(userId, request.getUserId());

        request.validateProfile();
        MultipartFile profileImg = request.getProfileImg();

        String filePath = awsS3Uploader.upload(profileImg, "user");
        Attach profile = member.getProfileImg().orElseGet(() -> request.getProfile(filePath));

        if(profile.getId() == null){
            attachRepository.save(profile);
        }else{
            profile.update(profileImg.getOriginalFilename(), filePath);
        }
    }

    @Override
    public MemberAccountResponse getAccountInfo() {
        Long userid = SecurityUtil.getCurrentUserId();
        Member member = memberRepository.findById(userid)
            .orElseThrow(UserNotFoundException::new);

        member.validate();
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
    public void followTeam(FollowRequest followRequest) {
        Member member = memberRepository.findById(followRequest.getMemberId())
                .orElseThrow(UserNotFoundException::new);

        Team team = teamRepository.findById(followRequest.getTeamId())
                .orElseThrow(UserNotFoundException::new);

        followRepository.findByMemberAndTeam(member, team)
                .ifPresentOrElse(Follow::toggle, ()->{
                    Follow newFollow = Follow.of(member, team);
                    followRepository.save(newFollow);
                });
    }

    @Override
    public void wishFunding(WishRequest wishRequest) {
        Member member = memberRepository.findById(wishRequest.getMemberId())
                .orElseThrow(UserNotFoundException::new);

        Funding funding = fundingRepository.findById(wishRequest.getFundingId())
                .orElseThrow(IllegalArgumentException::new);

        wishRepository.findByMemberAndFunding(member, funding)
                .ifPresentOrElse(Wish::toggle, ()->{
                    Wish newWish = Wish.of(member, funding);
                    wishRepository.save(newWish);
                });
    }

    @Override
    public MileageDetailResponse getMileageDetails(MileageDetailRequest request, Pageable pageable) {
        Long userId = SecurityUtil.getCurrentUserId();
        Member member = validateSameUser(userId, request.getUserId());

        PostGroup postGroup = request.getPostGroup();
        List<Payment> paymentList = paymentRepository.findAllByUserAndPostPostGroup(member, postGroup);
        return MileageDetailResponse.of(paymentList);
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

    private Member validateSameUser(Long i1, Long i2){
        if(i1 != i2)
            throw new IllegalArgumentException("동일 회원만 접근할 수 있습니다");

        return memberRepository.findById(i1).orElseThrow(UserNotFoundException::new);
    }

}