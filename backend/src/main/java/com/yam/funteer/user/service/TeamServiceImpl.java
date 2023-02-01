package com.yam.funteer.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.yam.funteer.attach.FileType;
import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.attach.entity.TeamAttach;
import com.yam.funteer.attach.repository.AttachRepository;
import com.yam.funteer.attach.repository.TeamAttachRepository;
import com.yam.funteer.common.aws.AwsS3Uploader;
import com.yam.funteer.common.security.SecurityUtil;
import com.yam.funteer.exception.DuplicateInfoException;
import com.yam.funteer.funding.repository.FundingRepository;
import com.yam.funteer.user.dto.request.team.UpdateTeamAccountRequest;
import com.yam.funteer.user.dto.request.team.UpdateTeamProfileRequest;
import com.yam.funteer.user.dto.response.team.TeamAccountResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

	 private final FundingRepository fundingRepository;
	private final FollowRepository followRepository;
	private final TeamRepository teamRepository;
	private final PasswordEncoder passwordEncoder;
	private final AttachRepository attachRepository;
	private final TeamAttachRepository teamAttachRepository;
	private final AwsS3Uploader awsS3Uploader;




	@Override
	public void createAccountWithOutProfile(CreateTeamRequest request) {
		teamRepository.findByEmail(request.getEmail()).ifPresent(team -> {
			throw new DuplicateInfoException("이메일이 중복됩니다");
		});

		request.encryptPassword(passwordEncoder);

		Team team = request.toTeam();
		teamRepository.save(team);

		request.validateFile();
		MultipartFile vmsFile = request.getVmsFile();
		MultipartFile performFile = request.getPerformFile();

		// 저장
		String vmsFilePath = awsS3Uploader.upload(vmsFile, "teamFile");
		String performFilePath = awsS3Uploader.upload(performFile, "teamFile");

		List<Attach> attachList = request.getAttachList(vmsFilePath, performFilePath);
		for(Attach attach : attachList){
			attachRepository.save(attach);
			TeamAttach teamAttach = TeamAttach.of(team, attach);
			teamAttachRepository.save(teamAttach);
		}

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
	public TeamProfileResponse getTeamProfile(Long userId) {
		Team team = teamRepository.findById(userId).orElseThrow(UserNotFoundException::new);

		List<Funding> fundingList = new ArrayList<>(); // fundingRepository.findAllByTeamId(team.getId());
		long followerCnt = followRepository.countAllByTeam(team);

		return TeamProfileResponse.of(team, fundingList, followerCnt);
	}

	@Override
	public void updateProfile(UpdateTeamProfileRequest request) {
		Long userId = SecurityUtil.getCurrentUserId();
		Team team = validateSameUser(userId, request.getUserId());

		// 파일 관리 시작
		MultipartFile bannerFile = request.getBanner();
		MultipartFile profileImgFile = request.getProfileImg();

		request.validateBannerAndProfile();

		String profilePath = awsS3Uploader.upload(profileImgFile, "user");
		String bannerPath = awsS3Uploader.upload(bannerFile, "user");

		Attach profile = team.getBanner().orElseGet(() -> request.getProfile(profilePath));
		Attach banner = team.getBanner().orElseGet(() -> request.getBanner(bannerPath));

		updateBannerOrProfile(profileImgFile.getOriginalFilename(), profilePath, profile);
		updateBannerOrProfile(bannerFile.getOriginalFilename(), bannerPath, banner);
		// 파일 관리 끝

		String description = request.getDescription();
		team.update(profile, banner, description);
	}

	@Override
	public TeamAccountResponse getTeamAccount() {
		Team team = teamRepository.findById(SecurityUtil.getCurrentUserId())
			.orElseThrow(UserNotFoundException::new);

		if(team.isResignOrWait()){
			throw new IllegalArgumentException("탈퇴하였거나 가입 대기중인 회원입니다.");
		}

		List<TeamAttach> teamAttaches = teamAttachRepository.findAllByTeamId(team.getId());
		TeamAccountResponse response = TeamAccountResponse.of(team);

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
			throw new IllegalArgumentException("비밀번호가 다릅니다");
		});
		team.validatePassword(passwordEncoder, password);

		request.getNewPassword().ifPresent(newPw->{
			String encryptedPw = passwordEncoder.encode(newPw);
			team.changePassword(encryptedPw);
		});

		request.getVmsFile().ifPresent(multipartFile -> {

		});

		request.getPerformFile().ifPresent(multipartFile -> {

		});
	}

	private void updateBannerOrProfile(String filename, String path, Attach attach){
		if(attach.getId() == null){
			attachRepository.save(attach);
		}else{
			attach.update(filename, path);
		}
	}

	private Team validateSameUser(Long i1, Long i2){
		if(i1 != i2) throw new IllegalArgumentException("동일 회원만 접근할 수 있습니다");

		return teamRepository.findById(i1).orElseThrow(UserNotFoundException::new);
	}

}
