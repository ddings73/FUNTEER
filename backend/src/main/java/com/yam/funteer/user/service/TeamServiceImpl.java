package com.yam.funteer.user.service;

import java.util.List;

import javax.transaction.Transactional;

import com.yam.funteer.attach.FileType;
import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.attach.entity.TeamAttach;
import com.yam.funteer.attach.repository.AttachRepository;
import com.yam.funteer.attach.repository.TeamAttachRepository;
import com.yam.funteer.badge.service.BadgeService;
import com.yam.funteer.common.aws.AwsS3Uploader;
import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.common.code.UserType;
import com.yam.funteer.common.security.SecurityUtil;
import com.yam.funteer.exception.DuplicateInfoException;
import com.yam.funteer.funding.repository.FundingRepository;
import com.yam.funteer.pay.entity.Payment;
import com.yam.funteer.pay.repository.PaymentRepository;
import com.yam.funteer.user.dto.request.team.UpdateTeamAccountRequest;
import com.yam.funteer.user.dto.request.team.UpdateTeamProfileRequest;
import com.yam.funteer.user.dto.response.team.TeamPaymentReceiptResponse;
import com.yam.funteer.user.dto.response.team.TeamAccountResponse;
import com.yam.funteer.user.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.user.dto.request.BaseUserRequest;
import com.yam.funteer.user.dto.request.team.CreateTeamRequest;
import com.yam.funteer.user.dto.response.team.TeamProfileResponse;
import com.yam.funteer.user.entity.Team;
import com.yam.funteer.user.repository.FollowRepository;
import com.yam.funteer.user.repository.TeamRepository;

@Service @Slf4j
@Transactional
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService{

	private final String teamFilePath = "teamFile";

	private final MemberRepository memberRepository;
	private final FundingRepository fundingRepository;
	private final FollowRepository followRepository;
	private final TeamRepository teamRepository;
	private final AttachRepository attachRepository;
	private final TeamAttachRepository teamAttachRepository;
	private final PasswordEncoder passwordEncoder;
	private final AwsS3Uploader awsS3Uploader;
	private final BadgeService badgeService;



	@Override
	public void createAccountWithOutProfile(CreateTeamRequest request) {
		teamRepository.findByEmail(request.getEmail()).ifPresent(team -> {
			throw new DuplicateInfoException("이메일이 중복됩니다");
		});

		request.encryptPassword(passwordEncoder);

		Team team = request.toTeam();
		teamRepository.save(team);
		badgeService.initBadges(team);

		request.validateFile();
		MultipartFile vmsFile = request.getVmsFile();
		MultipartFile performFile = request.getPerformFile();

		// 저장
		String vmsFilePath = awsS3Uploader.upload(vmsFile, teamFilePath);
		String performFilePath = awsS3Uploader.upload(performFile, teamFilePath);

		List<Attach> attachList = request.getAttachList(vmsFilePath, performFilePath);
		attachList.forEach(attach -> {
			attachRepository.save(attach);
			TeamAttach teamAttach = TeamAttach.of(team, attach);
			teamAttachRepository.save(teamAttach);
		});
	}
	@Override
	public void setAccountSignOut(BaseUserRequest baseUserRequest) {
		String password = baseUserRequest.getPassword().orElseThrow(()->
				new IllegalArgumentException("패스워드를 입력해주세요")
		);

		Long userId = SecurityUtil.getCurrentUserId();
		Team team = teamRepository.findById(userId).orElseThrow(UserNotFoundException::new);

		team.validatePassword(passwordEncoder, password);
		team.signOut();
	}
	@Override
	public TeamProfileResponse getTeamProfile(Long userId, Pageable pageable) {
		Team team = teamRepository.findById(userId).orElseThrow(UserNotFoundException::new);

		Page<Funding> fundingPage = fundingRepository.findAllByTeam(team, pageable);
		List<Funding> fundingList = fundingPage.getContent();

		long followerCnt = followRepository.countAllByTeam(team);

		TeamProfileResponse response = TeamProfileResponse.of(team, fundingList, followerCnt);
		if(SecurityUtil.isLogin()){
			Long currentUserId = SecurityUtil.getCurrentUserId();
			memberRepository.findById(currentUserId).ifPresent(member -> {
				followRepository.findByMemberAndTeam(member, team).ifPresent(follow -> {
					response.activeFollowBtn();
				});
			});
		}

		return response;
	}
	@Override
	public void updateProfile(UpdateTeamProfileRequest request) {
		Long userId = SecurityUtil.getCurrentUserId();
		Team team = validateSameUser(userId, request.getUserId());

		// 파일 관리 시작
		MultipartFile bannerFile = request.getBanner();
		MultipartFile profileImgFile = request.getProfileImg();


		if(profileImgFile != null) {
			request.validateProfile();
			String profilePath = awsS3Uploader.upload(profileImgFile, "user");
			Attach profile = team.getProfileImg().orElseGet(() -> request.getProfile(profilePath));

			if(profile.getId() == null){
				attachRepository.save(profile);
				team.updateProfile(profile);
			}else{
				profile.update(profileImgFile.getOriginalFilename(), profilePath);
			}
		}

		if(bannerFile != null) {
			request.validateBanner();
			String bannerPath = awsS3Uploader.upload(bannerFile, "user");
			Attach banner = team.getBanner().orElseGet(() -> request.getBanner(bannerPath));
			if(banner.getId() == null){
				attachRepository.save(banner);
				team.updateBanner(banner);
			}else{
				banner.update(bannerFile.getOriginalFilename(), bannerPath);
			}
		}

		String description = request.getDescription();
		team.updateDescription(description);
	}
	@Override
	public TeamAccountResponse getTeamAccount() {
		Team team = teamRepository.findById(SecurityUtil.getCurrentUserId())
			.orElseThrow(UserNotFoundException::new);

		List<TeamAttach> teamAttaches = teamAttachRepository.findAllByTeam(team);
		TeamAccountResponse response = TeamAccountResponse.from(team);

		teamAttaches.forEach(teamAttach -> {
			Attach attach = teamAttach.getAttach();
			FileType fileType = attach.getFileType();
			if(fileType.equals(FileType.VMS)){
				response.setVmsFileUrl(attach.getPath());
			}else if(fileType.equals(FileType.PERFORM)){
				response.setPerformFileUrl(attach.getPath());
			}
		});

		return response;
	}
	@Override
	public void updateAccount(UpdateTeamAccountRequest request) {
		Long userId = SecurityUtil.getCurrentUserId();
		Team team = validateSameUser(userId, request.getUserId());

		String password = request.getPassword().orElseThrow(()-> {
			throw new IllegalArgumentException("비밀번호가 필요합니다");
		});
		team.validatePassword(passwordEncoder, password);

		if(team.getUserType() == UserType.TEAM && request.hasFile()) {
			throw new AccessDeniedException("승인 대기, 혹은 만료된 회원만 파일을 수정할 수 있습니다.");
		}

		List<TeamAttach> teamAttaches = teamAttachRepository.findAllByTeam(team);

		request.getVmsFile().ifPresent(file -> updateTeamFile(file, teamAttaches, FileType.VMS));
		request.getPerformFile().ifPresent(file -> updateTeamFile(file, teamAttaches, FileType.PERFORM));

		request.getNewPassword().ifPresent(newPw->{
			String encryptedPw = passwordEncoder.encode(newPw);
			team.changePassword(encryptedPw);
		});


	}


	private void updateTeamFile(MultipartFile file, List<TeamAttach> teamAttaches, FileType fileType){
		String filePath = awsS3Uploader.upload(file, teamFilePath);
		Attach attach = Attach.of(file.getOriginalFilename(), filePath, fileType);
		teamAttaches.forEach(teamAttach -> {
			Attach savedAttach = teamAttach.getAttach();
			FileType savedFileType = savedAttach.getFileType();
			String path = savedAttach.getPath();
			if(savedFileType.equals(fileType)){
				teamAttach.updateAttach(attach);
				awsS3Uploader.delete(teamFilePath, path);
			}
		});
	}

	private Team validateSameUser(Long i1, Long i2){
		if(i1 != i2) throw new IllegalArgumentException("동일 회원만 접근할 수 있습니다");
		return teamRepository.findById(i1).orElseThrow(UserNotFoundException::new);
	}

}
