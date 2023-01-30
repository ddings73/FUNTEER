package com.yam.funteer.user.service;

import com.yam.funteer.attach.FileUtil;
import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.attach.repository.AttachRepository;
import com.yam.funteer.common.aws.AwsS3Uploader;
import com.yam.funteer.common.security.SecurityUtil;
import com.yam.funteer.exception.EmailDuplicateException;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.repository.FundingRepository;
import com.yam.funteer.user.dto.request.*;
import com.yam.funteer.user.dto.response.MemberAccountResponse;
import com.yam.funteer.user.dto.response.MemberProfileResponse;
import com.yam.funteer.user.entity.*;
import com.yam.funteer.user.repository.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

import java.io.IOException;
import java.util.Optional;

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


    @Override
    public void createAccountWithOutProfile(CreateMemberRequest request) {
        Optional<Member> findMember = memberRepository.findByEmail(request.getEmail());
        findMember.ifPresent(member -> {
            throw new EmailDuplicateException();
        });

        request.encryptPassword(passwordEncoder);
        Member member = request.toMember();
        memberRepository.save(member);
    }

    @Override
    public void setAccountSignOut(BaseUserRequest request) {
        Long userId = SecurityUtil.getCurrentUserId().orElseThrow();
        String password = request.getPassword()
                .orElseThrow(() -> new IllegalArgumentException("패스워드를 입력해주세요"));

        Member member = memberRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        member.validatePassword(passwordEncoder, password);
        member.signOut();
    }

    @Override
    public MemberProfileResponse getProfile(Long userId) {
        Member member = memberRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if(member.isResign()){
            throw new IllegalArgumentException("탈퇴한 회원입니다");
        }

        long followCnt = followRepository.countAllByMember(member);
        long wishCnt = wishRepository.countAllByMember(member);

        return MemberProfileResponse.of(member, wishCnt, followCnt);
    }

    @Override
    public void updateProfile(UpdateProfileRequest request) {
        Long userId = request.getUserId();
        validateSameUser(userId);

        Member member = memberRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        request.validateProfileType();
        MultipartFile profileImg = request.getProfileImg();

        String filePath = awsS3Uploader.upload(profileImg, "user");
        Optional<Attach> memberProfile = member.getProfileImg();
        memberProfile.ifPresentOrElse(attach -> {
            awsS3Uploader.delete(attach.getPath(), "user");
            attach.update(request, filePath);
        }, () ->{
            Attach saveImg = request.getAttach(filePath);
            attachRepository.save(saveImg);
            member.update(request, saveImg);
        });
    }

    @Override
    public MemberAccountResponse getAccount(Long userId) {
        validateSameUser(userId);
        Member member = memberRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if(member.isResign()){
            throw new IllegalArgumentException("탈퇴한 회원입니다");
        }
        return MemberAccountResponse.of(member);
    }

    @Override
    public void updateAccount(BaseUserRequest request) {
        Long userId = request.getUserId();
        validateSameUser(userId);
        
        Member member = memberRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        String newPassword = request.getPassword().orElseThrow(()->{
            throw new IllegalArgumentException("패스워드는 필수 입력 값입니다.");
        });
//        member.validatePassword(passwordEncoder, originPassword);
//
//        String newPassword = request.getNewPassword().orElseThrow(()->{
//            throw new IllegalArgumentException("패스워드는 필수 입력 값입니다.");
//        });

        String pw = passwordEncoder.encode(newPassword);
        member.changePassword(pw);
    }

    @Override
    public void followTeam(FollowRequest followRequest) {
        Optional<Member> findMember = memberRepository.findById(followRequest.getMemberId());
        Member member = findMember.orElseThrow(UserNotFoundException::new);

        Optional<Team> findTeam = teamRepository.findById(followRequest.getTeamId());
        Team team = findTeam.orElseThrow(UserNotFoundException::new);

        Optional<Follow> findFollow = followRepository.findByMemberAndTeam(member, team);
        findFollow.ifPresentOrElse(Follow::toggle, ()->{
            Follow newFollow = Follow.of(member, team);
            followRepository.save(newFollow);
        });
    }

    @Override
    public void wishFunding(WishRequest wishRequest) {
        Optional<Member> findMember = memberRepository.findById(wishRequest.getMemberId());
        Member member = findMember.orElseThrow(UserNotFoundException::new);

        Optional<Funding> findFunding = fundingRepository.findById(wishRequest.getFundingId());
        Funding funding = findFunding.orElseThrow(IllegalArgumentException::new);

        Optional<Wish> findWish = wishRepository.findByMemberAndFunding(member, funding);
        findWish.ifPresentOrElse(Wish::toggle, ()->{
            Wish newWish = Wish.of(member, funding);
            wishRepository.save(newWish);
        });
    }

    @Override
    public void chargeMileage(ChargeRequest chargeRequest) {
        Long userId = chargeRequest.getUserId();
        validateSameUser(userId);

        Member member = memberRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Long amount = chargeRequest.getAmount();

        Charge charge = chargeRequest.toEntity(member);
        chargeRepository.save(charge);
        member.charge(amount);
    }


    private void validateSameUser(Long userId){
        Long nowId = SecurityUtil.getCurrentUserId().orElseGet(null);
        if(nowId == null || userId != nowId){
            throw new IllegalArgumentException("동일 회원만 접근할 수 있습니다");
        }
    }

}