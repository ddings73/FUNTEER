package com.yam.funteer.user.service;

import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.attach.repository.AttachRepository;
import com.yam.funteer.common.aws.AwsS3Uploader;
import com.yam.funteer.exception.EmailDuplicateException;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.repository.FundingRepository;
import com.yam.funteer.user.dto.request.CreateAccountRequest;
import com.yam.funteer.user.dto.request.UpdateAccountRequest;
import com.yam.funteer.user.dto.request.UpdateProfileRequest;
import com.yam.funteer.user.dto.response.MemberAccountResponse;
import com.yam.funteer.user.dto.response.MemberProfileResponse;
import com.yam.funteer.user.dto.request.BaseUserRequest;
import com.yam.funteer.user.entity.Follow;
import com.yam.funteer.user.entity.Member;
import com.yam.funteer.user.entity.Team;
import com.yam.funteer.user.entity.Wish;
import com.yam.funteer.user.repository.FollowRepository;
import com.yam.funteer.user.repository.MemberRepository;
import com.yam.funteer.user.repository.TeamRepository;
import com.yam.funteer.user.repository.WishRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Optional;

@Service @Slf4j
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final AttachRepository attachRepository;
    private final AwsS3Uploader awsS3Uploader;
    private final FollowRepository followRepository;
    private final FundingRepository fundingRepository;
    private final WishRepository wishRepository;


    @Override
    public void createAccountWithOutProfile(CreateAccountRequest request) {
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
        Member member = getMember(request.getUserId());
        String password = getPassword(request);

        member.validatePassword(passwordEncoder, password);
        member.signOut();
    }

    @Override
    public MemberProfileResponse getProfile(Long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        long followCnt = followRepository.countAllByMember(member);
        long wishCnt = wishRepository.countAllByMember(member);

        return MemberProfileResponse.of(member, wishCnt, followCnt);
    }

    @Override
    public void updateProfile(UpdateProfileRequest request) {
        Member member = getMember(request.getUserId());

        MultipartFile profileImg = request.getProfileImg();
        String filename = null;
        try {
            filename = awsS3Uploader.upload(profileImg, "/user");
        } catch (Exception e){
            e.printStackTrace();
        }

        Optional<Attach> memberProfile = member.getProfileImg();
        memberProfile.ifPresent(attach -> {
            awsS3Uploader.delete(attach.getPath(), "/user");
            attachRepository.delete(attach);
        });

        Attach savedImg = request.getAttach(filename);
        attachRepository.save(savedImg);

        member.update(request, savedImg);
    }

    @Override
    public MemberAccountResponse getAccount(Long userId) {
        Member member = getMember(userId);
        return MemberAccountResponse.of(member);
    }

    @Override
    public void updateAccount(UpdateAccountRequest request) {
        Member member = getMember(request.getUserId());
        String originPassword = getPassword(request);
        member.validatePassword(passwordEncoder, originPassword);

        String newPassword = request.getNewPassword().orElseThrow(IllegalArgumentException::new);
        String pw = passwordEncoder.encode(newPassword);
        member.changePassword(pw);
    }

    @Override
    public void followTeam(Long teamId, Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = findMember.orElseThrow(UserNotFoundException::new);

        Optional<Team> findTeam = teamRepository.findById(teamId);
        Team team = findTeam.orElseThrow(UserNotFoundException::new);

        Optional<Follow> findFollow = followRepository.findByMemberAndTeam(member, team);
        findFollow.ifPresentOrElse(Follow::toggle, ()->{
            Follow newFollow = Follow.of(member, team);
            followRepository.save(newFollow);
        });
    }

    @Override
    public void wishFunding(Long fundingId, Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = findMember.orElseThrow(UserNotFoundException::new);

        Optional<Funding> findFunding = fundingRepository.findById(fundingId);
        Funding funding = findFunding.orElseThrow(IllegalArgumentException::new);

        Optional<Wish> findWish = wishRepository.findByMemberAndFunding(member, funding);
        findWish.ifPresentOrElse(Wish::toggle, ()->{
            Wish newWish = Wish.of(member, funding);
            wishRepository.save(newWish);
        });
    }

    private Member getMember(Long userId){
        Optional<Member> findMember = memberRepository.findById(userId);
        return findMember.orElseThrow(UserNotFoundException::new);
    }

    private String getPassword(BaseUserRequest request){
        Optional<String> password = request.getPassword();
        return password.orElseThrow(IllegalArgumentException::new);
    }

}