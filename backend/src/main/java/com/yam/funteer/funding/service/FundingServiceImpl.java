package com.yam.funteer.funding.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.yam.funteer.attach.FileType;
import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.attach.repository.AttachRepository;
import com.yam.funteer.badge.service.BadgeService;
import com.yam.funteer.common.aws.AwsS3Uploader;
import com.yam.funteer.common.code.TargetMoneyType;
import com.yam.funteer.common.code.UserType;
import com.yam.funteer.common.security.SecurityUtil;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.funding.dto.request.FundingCommentRequest;
import com.yam.funteer.funding.dto.request.FundingReportDetailRequest;
import com.yam.funteer.funding.dto.request.TargetMoneyDetailRequest;
import com.yam.funteer.funding.dto.request.TargetMoneyRequest;
import com.yam.funteer.funding.dto.response.CommentResponse;
import com.yam.funteer.funding.dto.response.FundingDetailResponse;
import com.yam.funteer.funding.dto.response.FundingListPageResponse;
import com.yam.funteer.funding.dto.response.FundingListResponse;
import com.yam.funteer.funding.dto.request.FundingReportRequest;
import com.yam.funteer.funding.dto.response.FundingReportResponse;
import com.yam.funteer.funding.dto.request.FundingRequest;
import com.yam.funteer.funding.dto.request.TakeFundingRequest;
import com.yam.funteer.funding.entity.Category;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.entity.Report;
import com.yam.funteer.funding.entity.ReportDetail;
import com.yam.funteer.funding.entity.TargetMoney;
import com.yam.funteer.funding.entity.TargetMoneyDetail;
import com.yam.funteer.funding.exception.CategoryNotFoundException;
import com.yam.funteer.funding.exception.CommentNotFoundException;
import com.yam.funteer.funding.exception.FundingNotFoundException;
import com.yam.funteer.funding.exception.InsufficientBalanceException;
import com.yam.funteer.funding.exception.NotAuthenticatedMemberException;
import com.yam.funteer.funding.exception.NotAuthenticatedTeamException;
import com.yam.funteer.funding.exception.NotFoundReportException;
import com.yam.funteer.funding.repository.FundingRepository;
import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.funding.repository.ReportDetailRepository;
import com.yam.funteer.funding.repository.ReportRepository;
import com.yam.funteer.funding.repository.TargetMoneyDetailRepository;
import com.yam.funteer.funding.repository.TargetMoneyRepository;
import com.yam.funteer.pay.entity.Payment;
import com.yam.funteer.pay.repository.PaymentRepository;
import com.yam.funteer.post.entity.Comment;
import com.yam.funteer.post.entity.Hashtag;
import com.yam.funteer.post.entity.PostHashtag;
import com.yam.funteer.post.repository.CategoryRepository;
import com.yam.funteer.post.repository.CommentRepository;
import com.yam.funteer.post.repository.HashTagRepository;
import com.yam.funteer.post.repository.PostHashtagRepository;
import com.yam.funteer.post.repository.PostRepository;
import com.yam.funteer.user.entity.Member;
import com.yam.funteer.user.entity.Team;
import com.yam.funteer.user.entity.User;
import com.yam.funteer.user.entity.Wish;
import com.yam.funteer.user.repository.MemberRepository;
import com.yam.funteer.user.repository.TeamRepository;
import com.yam.funteer.user.repository.UserRepository;
import com.yam.funteer.user.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FundingServiceImpl implements FundingService{
	private static final String VIEWCOOKIENAME = "alreadyViewCookie";
	private final UserRepository userRepository;
	private final AttachRepository attachRepository;
	private final ReportDetailRepository reportDetailRepository;
	private final WishRepository wishRepository;
	private final PostRepository postRepository;

	private final ReportRepository reportRepository;
	private final TeamRepository teamRepository;
	private final PaymentRepository paymentRepository;
	private final MemberRepository memberRepository;
	private final CategoryRepository categoryRepository;

	private final FundingRepository fundingRepository;
	private final TargetMoneyRepository targetMoneyRepository;
	private final HashTagRepository hashTagRepository;
	private final AwsS3Uploader awsS3Uploader;

	private final PostHashtagRepository postHashtagRepository;

	private final CommentRepository commentRepository;

	private final TargetMoneyDetailRepository targetMoneyDetailRepository;

	private final BadgeService badgeService;

	@Override
	public Page<FundingListResponse> findAllFundingByAdmin(Pageable pageable) {
		return fundingRepository.findAll(pageable).map(FundingListResponse::from);
	}

	@Override
	public Page<FundingListResponse> findFundingByKeyword(String keyword, Pageable pageable) {
		List<Funding> collect = fundingRepository.findAllByTitleContainingOrContentContaining(keyword, keyword);
		return getFundingListResponses(
			pageable, collect);
	}

	@NotNull
	private static Page<FundingListResponse> getFundingListResponses(Pageable pageable, List<Funding> collect) {
		List<Funding> fundingArrayList = new ArrayList<>();

		for (Funding funding: collect)
			if (isaBoolean(funding)) {
				fundingArrayList.add(funding);
				log.info(funding.getFundingId().toString());
			}
		List<FundingListResponse> responses = fundingArrayList.stream()
			.map(FundingListResponse::from)
			.collect(Collectors.toList());
		Page<FundingListResponse> fundingListResponses = new PageImpl<>(responses, pageable, responses.size());
		return fundingListResponses;
	}

	@Override
	public Page<FundingListResponse> findFundingByHashtag(String hashtag, Pageable pageable) {
		// 해시태그 없으면 발생하는 Exception 넣기
		Long hashtagId = hashTagRepository.findByName(hashtag).get().getId();
		List<PostHashtag> byHashtag = postHashtagRepository.findByHashtagId(hashtagId);
		List<Funding> posts = new ArrayList<>();
		for (PostHashtag postHashtag : byHashtag) {
			Funding funding = fundingRepository.findByFundingId(postHashtag.getPost().getId()).orElseThrow(FundingNotFoundException::new);
			if (isaBoolean(funding)) {
				posts.add(funding);
			}
		}

		List<FundingListResponse> collect = posts.stream().map(m -> FundingListResponse.from(m)).collect(Collectors.toList());
		Page<FundingListResponse> collect2 = new PageImpl<>(collect.subList(0, collect.size()), pageable, collect.size());
		return collect2;
	}

	private static boolean isaBoolean(Funding funding) {
		boolean b = funding.getPostType() != PostType.FUNDING_FAIL && funding.getPostType() != PostType.FUNDING_WAIT
			&& funding.getPostType() != PostType.FUNDING_REJECT;
		return b;
	}

	@Override
	public int updateHit(Long fundingId, HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		boolean checkCookie = false;
		int result = 0;
		if(cookies != null){
			for (Cookie cookie : cookies)
			{
				// 이미 조회를 한 경우 체크
				if (cookie.getName().equals(VIEWCOOKIENAME+fundingId)) checkCookie = true;

			}
			if(!checkCookie){
				Cookie newCookie = createCookieForForNotOverlap(fundingId);
				response.addCookie(newCookie);
				result = fundingRepository.updateHit(fundingId);
			}
		} else {
			Cookie newCookie = createCookieForForNotOverlap(fundingId);
			response.addCookie(newCookie);
			result = fundingRepository.updateHit(fundingId);
		}
		return result;
	}

	/*
	 * 조회수 중복 방지를 위한 쿠키 생성 메소드
	 * @param cookie
	 * @return
	 * */
	private Cookie createCookieForForNotOverlap(Long fundingId) {
		Cookie cookie = new Cookie(VIEWCOOKIENAME+fundingId, String.valueOf(fundingId));
		cookie.setComment("조회수 중복 증가 방지 쿠키");	// 쿠키 용도 설명 기재
		cookie.setMaxAge(getRemainSecondForTomorrow()); 	// 하루를 준다.
		cookie.setHttpOnly(true);				// 서버에서만 조작 가능
		return cookie;
	}

	// 다음 날 정각까지 남은 시간(초)
	private int getRemainSecondForTomorrow() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime tomorrow = LocalDateTime.now().plusDays(1L).truncatedTo(ChronoUnit.DAYS);
		return (int) now.until(tomorrow, ChronoUnit.SECONDS);
	}

	@Override
	public Page<FundingListResponse> findFundingByCategory(Long categoryId, Pageable pageable) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
		List<Funding> collect = fundingRepository.findAllByCategory(category);
		Page<FundingListResponse> fundingListResponses = getFundingListResponses(
			pageable, collect);
		return fundingListResponses;
	}

	@Override
	public FundingListPageResponse findAllFunding(Pageable pageable) {
		List<Funding> collect = fundingRepository.findAll();

		Page<FundingListResponse> fundingListResponses = getFundingListResponses(
			pageable, collect);

		List<Funding> successFundingList = fundingRepository.findAllByPostType(PostType.REPORT_ACCEPT);

		Long inProgressFundingAmount = fundingRepository.findAllByPostType(PostType.FUNDING_IN_PROGRESS).stream().count();
		inProgressFundingAmount += fundingRepository.findAllByPostType(PostType.FUNDING_EXTEND).stream().count();
		int successFundingCount = successFundingList.size();

		Long totalFundingAmount = 0L;
		for (Funding funding : successFundingList) {
			totalFundingAmount += funding.getCurrentFundingAmount();
		}

		return new FundingListPageResponse(fundingListResponses, inProgressFundingAmount, successFundingCount, totalFundingAmount);
	}


	@Override
	public FundingDetailResponse createFunding(FundingRequest data) {

		// 인증 완료된 team 아니면 펀딩 작성 못함
		Long currentUserId = SecurityUtil.getCurrentUserId();
		Team team = teamRepository.findById(currentUserId).orElseThrow();

		if (team.getUserType() != UserType.TEAM) {
			throw new NotAuthenticatedTeamException("인증되지 않은 단체입니다.");
		}

		// category 들고오기
		Category category = categoryRepository.findById(data.getCategoryId()).orElseThrow();


		// time 변환
		LocalDate startDate = LocalDate.parse(data.getStartDate(),
			DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		LocalDate endDate = LocalDate.parse(data.getEndDate(),
			DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		// 펀딩 생성
		Funding funding = Funding.builder()
			.team(team)
			.title(data.getTitle())
			.category(category)
			.content(data.getContent())
			.startDate(startDate)
			.endDate(endDate)
			.regDate(LocalDateTime.now())
			.hit(0)
			.currentFundingAmount(0L)
			.thumbnail(data.getThumbnail())
			.postGroup(PostGroup.FUNDING)
			.postType(PostType.FUNDING_WAIT)
			.fundingDescription(data.getFundingDescription())
			.build();

		Funding savedPost = fundingRepository.save(funding);

		addTargetMoney(data, savedPost);

		List<Hashtag> hashtagList = parseHashTags(data.getHashtags());
		List<Hashtag> hashtags = saveNotExistHashTags(hashtagList);
		addPostHashtags(funding, hashtags);

		return FundingDetailResponse.from(savedPost);

	}

	private void addPostHashtags(Funding funding, List<Hashtag> hashtagList) {
		List<PostHashtag> postHashtagList = new ArrayList<>();
		for (Hashtag hashtag : hashtagList) {
			PostHashtag postHashtag = new PostHashtag(funding, hashtag);
			postHashtagRepository.save(postHashtag);
			postHashtagList.add(postHashtag);
		}

		funding.setHashtags(postHashtagList);
	}

	private void addTargetMoney(FundingRequest data, Funding funding) {
		List<TargetMoney> targetMoneyList = new ArrayList<>();

		setTargetMoneyListByLevel(targetMoneyList, funding, data.getTargetMoneyLevelOne());
		setTargetMoneyListByLevel(targetMoneyList, funding, data.getTargetMoneyLevelTwo());
		setTargetMoneyListByLevel(targetMoneyList, funding, data.getTargetMoneyLevelThree());

		funding.setTargetMoneyList(targetMoneyList);
	}

	private void setTargetMoneyListByLevel(List<TargetMoney> targetMoneyList, Funding funding, TargetMoneyRequest targetMoneyRequest) {
		String[] split = targetMoneyRequest.getAmount().split(",");
		int amount = Integer.parseInt(String.join("", split));

		TargetMoney targetMoney = TargetMoney.builder()
			.amount(amount)
			.funding(funding)
			.targetMoneyType(TargetMoneyType.LEVEL_ONE)
			.build();

		targetMoneyRepository.save(targetMoney);

		if (targetMoneyRequest.getTargetMoneyType().equals("LEVEL_ONE")) {
			targetMoney.setTargetMoneyType(TargetMoneyType.LEVEL_ONE);
		} else if (targetMoneyRequest.getTargetMoneyType().equals("LEVEL_TWO")) {
			targetMoney.setTargetMoneyType(TargetMoneyType.LEVEL_TWO);
		} else if (targetMoneyRequest.getTargetMoneyType().equals("LEVEL_THREE")) {
			targetMoney.setTargetMoneyType(TargetMoneyType.LEVEL_THREE);
		}


		List<TargetMoneyDetail> targetMoneyDetails = new ArrayList<>();
		for (TargetMoneyDetailRequest targetMoneyDetailRequest : targetMoneyRequest.getDescriptions()) {
			TargetMoneyDetail targetMoneyDetail = new TargetMoneyDetail(targetMoney, targetMoneyDetailRequest.getDescription());
			targetMoneyDetailRepository.save(targetMoneyDetail);
			targetMoneyDetails.add(targetMoneyDetail);
		}

		targetMoney.setTargetMoneyDescriptions(targetMoneyDetails);
		targetMoneyList.add(targetMoney);
	}

	private List<Hashtag> saveNotExistHashTags(List<Hashtag> hashtagList) {
		List<Hashtag> hashtags = new ArrayList<>();
		for(Hashtag hashtag : hashtagList) {
			Optional<Hashtag> oneByName = hashTagRepository.findOneByName(hashtag.getName());
			if (oneByName.isEmpty()) {
				Hashtag save = hashTagRepository.save(hashtag);
				hashtags.add(save);
			}else{
				hashtags.add(oneByName.get());
			}
		}
		return hashtags;

	}
	private List<Hashtag> parseHashTags(String hashtags) {
		String[] split = hashtags.split("#");
		String[] split2 = Arrays.copyOfRange(split, 1, split.length);
		List<Hashtag> collect = Arrays.stream(split2)
			.map(m -> new Hashtag(m)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public FundingDetailResponse findFundingById(Long id, Pageable pageable) {
		Funding funding = fundingRepository.findByFundingId(id).orElseThrow(FundingNotFoundException::new);
		FundingDetailResponse fundingDetailResponse = FundingDetailResponse.from(funding);
		long wishCount = wishRepository.countAllByFundingIdAndChecked(id, true);
		fundingDetailResponse.setWishCount(wishCount);
		Long tempId = funding.getId();

		User user = userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(UserNotFoundException::new);
		if (user.getUserType() == UserType.NORMAL || user.getUserType() == UserType.KAKAO ) {
			Member member = memberRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(UserNotFoundException::new);
			Optional<Wish> byMemberAndFunding = wishRepository.findByMemberAndFunding(member, funding);
			boolean isWished = byMemberAndFunding.isPresent() ? byMemberAndFunding.get().getChecked() : false;
			fundingDetailResponse.setIsWished(isWished);
		}

		// 목표금액
		fundingDetailResponse.setTargetMoneyListLevelOne(targetMoneyRepository.findByFundingFundingIdAndTargetMoneyType(
			id, TargetMoneyType.LEVEL_ONE));
		fundingDetailResponse.setTargetMoneyListLevelTwo(targetMoneyRepository.findByFundingFundingIdAndTargetMoneyType(
			id, TargetMoneyType.LEVEL_TWO));
		fundingDetailResponse.setTargetMoneyListLevelThree(targetMoneyRepository.findByFundingFundingIdAndTargetMoneyType(
			id, TargetMoneyType.LEVEL_THREE));

		Page<CommentResponse> collect = commentRepository.findAllByFundingId(tempId, pageable).map(m -> CommentResponse.from(m));
		fundingDetailResponse.setComments(Optional.of(collect));

		return fundingDetailResponse;
	}

	@Override
	public FundingDetailResponse updateFunding(Long fundingId, FundingRequest data) {
		Funding funding = fundingRepository.findByFundingId(fundingId).orElseThrow(FundingNotFoundException::new);
		Team team = teamRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(UserNotFoundException::new);

		if (funding.getTeam() != team) {
			throw new NotAuthenticatedTeamException("수정 권한이 없습니다.");
		}

		LocalDate endDate = LocalDate.parse(data.getEndDate(),
			DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		if (funding.getPostType() == PostType.FUNDING_REJECT) {

			// 기존 파일 삭제, 새로운 파일 추가
			awsS3Uploader.delete("thumbnails/", funding.getThumbnail());

			Category category = categoryRepository.findById(data.getCategoryId()).orElseThrow(
				CategoryNotFoundException::new);

			setTargetMoney(data, funding);

			List<PostHashtag> postHashtagList = funding.getHashtags();
			postHashtagRepository.deleteAll(postHashtagList);

			List<Hashtag> hashtagList = parseHashTags(data.getHashtags());
			List<Hashtag> hashtags = saveNotExistHashTags(hashtagList);

			addPostHashtags(funding, hashtags);

			LocalDate startDate = LocalDate.parse(data.getStartDate(),
			DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			funding.setStartDate(startDate);
			funding.setEndDate(endDate);
			funding.setContent(data.getContent());
			funding.setTitle(data.getTitle());
			funding.setCategory(category);
			funding.setThumbnail(data.getThumbnail());
			funding.setPostType(PostType.FUNDING_WAIT);
			funding.setRegDate(LocalDateTime.now());

		} else if (funding.getPostType() == PostType.FUNDING_IN_PROGRESS) {
			funding.setEndDate(endDate);
			funding.setPostType(PostType.FUNDING_EXTEND);
		}

		return FundingDetailResponse.from(funding);
	}

	private void setTargetMoney(FundingRequest data, Funding funding) {
		List<TargetMoney> targetMoneyList = new ArrayList<>();
		for (TargetMoney targetMoney : funding.getTargetMoneyList()) {
			targetMoneyRepository.delete(targetMoney);
		}

		setTargetMoneyListByLevel(targetMoneyList, funding,  data.getTargetMoneyLevelOne());
		setTargetMoneyListByLevel(targetMoneyList, funding,  data.getTargetMoneyLevelTwo());
		setTargetMoneyListByLevel(targetMoneyList, funding,  data.getTargetMoneyLevelThree());
		funding.setTargetMoneyList(targetMoneyList);
	}

	@Override
	public void deleteFunding(Long fundingId) {
		Funding funding = fundingRepository.findByFundingId(fundingId).orElseThrow(FundingNotFoundException::new);
		awsS3Uploader.delete("thumbnails/" , funding.getThumbnail());
		fundingRepository.delete(funding);
		postRepository.delete(funding);
	}

	@Override
	public FundingReportResponse createFundingReport(Long fundingId, FundingReportRequest data) {
		Funding funding = fundingRepository.findByFundingId(fundingId).orElseThrow(FundingNotFoundException::new);
		String receiptUrl = awsS3Uploader.upload(data.getReceiptFile(), "reports/" + fundingId);

		Report report = new Report(funding, data.getContent(), LocalDateTime.now());
		reportRepository.save(report);

		Attach attach = Attach.builder()
			.name(fundingId + "-receiptFIle")
			.fileType(FileType.RECEIPT)
			.path(receiptUrl)
			.regDate(LocalDateTime.now())
			.build();

		List<ReportDetail> reportDetails = getReportDetails(data, report, attach);

		report.setReportDetail(reportDetails);
		funding.setPostType(PostType.REPORT_WAIT);
		return FundingReportResponse.from(report);

	}

	private List<ReportDetail> getReportDetails(FundingReportRequest data, Report report, Attach attach) {
		attachRepository.save(attach);

		List<ReportDetail> reportDetails = new ArrayList<>();
		for (FundingReportDetailRequest fundingReportDetailRequest : data.getFundingDetailRequests()) {

			String[] split = fundingReportDetailRequest.getAmount().split(",");
			Long result = Long.parseLong(String.join("", split));

			ReportDetail reportDetail = new ReportDetail(report, fundingReportDetailRequest.getDescription(),
				result);
			reportDetailRepository.save(reportDetail);
			reportDetails.add(reportDetail);
		}
		return reportDetails;
	}

	@Override
	public FundingReportResponse findFundingReportById(Long fundingId) {
		Report byFundingId = reportRepository.findByFundingFundingId(fundingId).orElseThrow(NotFoundReportException::new);
		return FundingReportResponse.from(byFundingId);
	}

	@Override
	public FundingReportResponse updateFundingReport(Long fundingId, FundingReportRequest data) {
		Report report = reportRepository.findByFundingFundingId(fundingId).orElseThrow(NotFoundReportException::new);
		Funding funding = fundingRepository.findByFundingId(fundingId).orElseThrow(FundingNotFoundException::new);

		awsS3Uploader.delete("reports/" + fundingId + "/", report.getReceipts().getPath());
		attachRepository.delete(report.getReceipts());
		String receiptUrl = awsS3Uploader.upload(data.getReceiptFile(), "reports/" + fundingId);

		Attach attach = Attach.builder()
			.name(fundingId + "-receiptFile")
			.fileType(FileType.RECEIPT)
			.path(receiptUrl)
			.regDate(LocalDateTime.now())
			.build();

		List<ReportDetail> reportDetails = getReportDetails(data, report, attach);

		report.setReportReceipts(attach);
		report.setReportContent(data.getContent());
		report.setReportDetail(reportDetails);
		report.setReportRegDate(LocalDateTime.now());
		funding.setPostType(PostType.REPORT_WAIT);

		return FundingReportResponse.from(report);
	}

	@Override
	public void createFundingComment(Long fundingId, FundingCommentRequest data) {
		Funding funding = fundingRepository.findByFundingId(fundingId).orElseThrow(FundingNotFoundException::new);
		Long userId = SecurityUtil.getCurrentUserId();
		Member member = memberRepository.findById(userId).orElseThrow();

		Comment comment = new Comment(funding, member, data.getContent());
		commentRepository.save(comment);
	}

	@Override
	public void deleteFundingComment(Long commentId) {
		Long userId = SecurityUtil.getCurrentUserId();
		Member member = memberRepository.findById(userId).orElseThrow(UserNotFoundException::new);
		Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);

		if (comment.getMember() != member) {
			throw new NotAuthenticatedMemberException("댓글을 삭제할 권한이 없습니다.");
		}
		commentRepository.delete(comment);
	}

	@Override
	public void takeFunding(Long fundingId, TakeFundingRequest data) {

		Funding funding = fundingRepository.findByFundingId(fundingId).orElseThrow(FundingNotFoundException::new);

		Long memberId = SecurityUtil.getCurrentUserId();
		Member member = memberRepository.findById(memberId).orElseThrow(UserNotFoundException::new);

		String[] split = data.getAmount().split(",");
		Long amount = Long.parseLong(String.join("", split));

		if (member.getMoney() < amount) {
			throw new InsufficientBalanceException();
		}

		Payment payment = Payment.builder()
			.amount(amount)
			.post(funding)
			.payDate(LocalDateTime.now())
			.user(member)
			.build();

		paymentRepository.save(payment);

		member.updateMoney(-amount);
		funding.setCurrentFundingAmount(funding.getCurrentFundingAmount() + amount);

		badgeService.postBadges(member, PostGroup.FUNDING);
		badgeService.totalPayAmount(member);

	}

	// 자정이 되면 StartDate 가 당일인 펀딩들 중 승인 안료된 펀딩을 진행중으로 변경, 펀딩 금액에 따라 완료/실패 여부 판단
	@Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
	public void changeStatusFunding() {
		List<Funding> all = fundingRepository.findAllByStartDate(LocalDate.now());
		for (Funding funding : all) {
			if (funding.getPostType() == PostType.FUNDING_ACCEPT) {
				funding.setPostType(PostType.FUNDING_IN_PROGRESS);
			}
		}

		List<Funding> allByEndDate = fundingRepository.findAllByEndDate(LocalDate.now().minusDays(1));

		for (Funding funding : allByEndDate) {

			Long targetAmount = 0L;

			for (TargetMoney targetMoney : funding.getTargetMoneyList()) {
				if (targetMoney.getTargetMoneyType() == TargetMoneyType.LEVEL_ONE) {
					targetAmount += targetMoney.getAmount();
				}
			}

			if (funding.getCurrentFundingAmount() >= targetAmount) {
				funding.setPostType(PostType.FUNDING_COMPLETE);
			} else {
				funding.setPostType(PostType.FUNDING_FAIL);
			}
		}
	}

}
