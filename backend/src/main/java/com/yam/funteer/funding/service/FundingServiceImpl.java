package com.yam.funteer.funding.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yam.funteer.attach.FileType;
import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.attach.repository.AttachRepository;
import com.yam.funteer.badge.service.BadgeService;
import com.yam.funteer.common.aws.AwsS3Uploader;
import com.yam.funteer.common.code.TargetMoneyType;
import com.yam.funteer.common.code.UserType;
import com.yam.funteer.common.security.SecurityUtil;
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
import com.yam.funteer.funding.exception.CommentNotFoundException;
import com.yam.funteer.funding.exception.FundingNotFoundException;
import com.yam.funteer.funding.exception.InsufficientBalanceException;
import com.yam.funteer.funding.exception.NotAuthenticatedTeamException;
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
import com.yam.funteer.user.repository.MemberRepository;
import com.yam.funteer.user.repository.TeamRepository;
import com.yam.funteer.user.repository.WishRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FundingServiceImpl implements FundingService{
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
	public Page<FundingListResponse> findFundingByKeyword(String keyword, Pageable pageable) {
		Page<FundingListResponse> collect = fundingRepository.findAllByTitleContainingOrContentContaining(keyword, keyword,
			pageable).map(m -> FundingListResponse.from(m));
		return collect;
	}

	@Override
	public Page<FundingListResponse> findFundingByHashtag(String hashtag, Pageable pageable) {
		// 해시태그 없으면 발생하는 Exception 넣기
		Long hashtagId = hashTagRepository.findByName(hashtag).get().getId();
		List<PostHashtag> byHashtag = postHashtagRepository.findByHashtagId(hashtagId);
		List<Funding> posts = new ArrayList<>();
		for (PostHashtag postHashtag : byHashtag) {
			Optional<Funding> funding = fundingRepository.findByFundingId(postHashtag.getPost().getId());
			posts.add(funding.get());
		}
		List<FundingListResponse> collect = posts.stream().map(m -> FundingListResponse.from(m)).collect(Collectors.toList());
		Page<FundingListResponse> collect2 = new PageImpl<>(collect.subList(0, collect.size()), pageable, collect.size());
		return collect2;

	}


	@Override
	public Page<FundingListResponse> findFundingByCategory(Long categoryId, Pageable pageable) {
		Category category = categoryRepository.findById(categoryId).orElseThrow();
		Page<FundingListResponse> collect = fundingRepository.findAllByCategory(category, pageable).map(m -> FundingListResponse.from(m));
		return collect;
	}

	@Override
	public FundingListPageResponse findAllFunding(Pageable pageable) {
		Page<FundingListResponse> collect = fundingRepository.findAll(pageable).map(m -> FundingListResponse.from(m));
		List<Funding> successFundingList = fundingRepository.findAllByPostType(PostType.REPORT_ACCEPT);

		Long totalFundingCount = fundingRepository.findAll().stream().count();
		int successFundingCount = successFundingList.size();

		Long totalFundingAmount = 0L;
		for (Funding funding : successFundingList) {
			totalFundingAmount += funding.getCurrentFundingAmount();
		}

		FundingListPageResponse fundingListPageResponse = new FundingListPageResponse(collect, totalFundingCount, successFundingCount, totalFundingAmount);
		return fundingListPageResponse;
	}


	@Override
	public FundingDetailResponse createFunding(FundingRequest data) throws
		IOException,
		NotAuthenticatedTeamException {
		// 인증 완료된 team 아니면 펀딩 작성 못함
		Long currentUserId = SecurityUtil.getCurrentUserId();
		Team team = teamRepository.findById(currentUserId).orElseThrow();

		if (team.getUserType() != UserType.TEAM) {
			throw new NotAuthenticatedTeamException();
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

		// s3 변환
		// String thumbnailUrl = awsS3Uploader.upload(thumbnail, "thumbnails/" + savedPost.getId());
		//
		// savedPost.setThumbnail(thumbnailUrl);

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
		Funding funding = fundingRepository.findByFundingId(id).orElseThrow(() -> new IllegalArgumentException());
		FundingDetailResponse fundingDetailResponse = FundingDetailResponse.from(funding);
		long wishCount = wishRepository.countAllByFundingIdAndChecked(id, true);
		fundingDetailResponse.setWishCount(wishCount);
		Long tempId = funding.getId();

		// 목표금액
		fundingDetailResponse.setTargetMoneyListLevelOne(targetMoneyRepository.findByFundingFundingIdAndTargetMoneyType(
			id, TargetMoneyType.LEVEL_ONE));
		fundingDetailResponse.setTargetMoneyListLevelTwo(targetMoneyRepository.findByFundingFundingIdAndTargetMoneyType(
			id, TargetMoneyType.LEVEL_TWO));
		fundingDetailResponse.setTargetMoneyListLevelThree(targetMoneyRepository.findByFundingFundingIdAndTargetMoneyType(
			id, TargetMoneyType.LEVEL_THREE));

		Page<CommentResponse> collect = commentRepository.findAllByFundingId(tempId, pageable).map(m -> CommentResponse.from(m));
		System.out.println(collect);
		fundingDetailResponse.setComments(Optional.of(collect));
		System.out.println(fundingDetailResponse);


		return fundingDetailResponse;
	}

	@Override
	public FundingDetailResponse updateFunding(Long fundingId, FundingRequest data) throws Exception {
		Funding funding = fundingRepository.findByFundingId(fundingId).orElseThrow(() -> new FundingNotFoundException());

		LocalDate endDate = LocalDate.parse(data.getEndDate(),
			DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		if (funding.getPostType() == PostType.FUNDING_REJECT) {


			// 기존 파일 삭제, 새로운 파일 추가
			awsS3Uploader.delete("thumbnails/", funding.getThumbnail());

			Category category = categoryRepository.findById(data.getCategoryId()).orElseThrow();

			setTargetMoney(data, funding);

			List<PostHashtag> postHashtagList = funding.getHashtags();
			for (PostHashtag postHashtag : postHashtagList) {
				postHashtagRepository.delete(postHashtag);
			}

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
		} else {
			throw new Exception("no");
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
	public void deleteFunding(Long fundingId) throws FundingNotFoundException {
		Funding funding = fundingRepository.findByFundingId(fundingId).orElseThrow(() -> new FundingNotFoundException());
		awsS3Uploader.delete("thumbnails/" , funding.getThumbnail());
		fundingRepository.delete(funding);
		postRepository.delete(funding);
	}

	@Override
	public FundingReportResponse createFundingReport(Long fundingId, FundingReportRequest data) {
		Funding funding = fundingRepository.findByFundingId(fundingId).orElseThrow();
		String receiptUrl = awsS3Uploader.upload(data.getReceiptFile(), "reports/" + fundingId);

		Attach attach = Attach.builder()
			.name(fundingId + "-receiptFIle")
			.fileType(FileType.RECEIPT)
			.path(receiptUrl)
			.regDate(LocalDateTime.now())
			.build();

		attachRepository.save(attach);

		Report report = new Report(funding, data.getContent(), LocalDateTime.now(), attach);
		reportRepository.save(report);

		List<ReportDetail> reportDetails = new ArrayList<>();
		for (FundingReportDetailRequest fundingReportDetailRequest : data.getFundingDetailRequests()) {

			String[] split = fundingReportDetailRequest.getAmount().split(",");
			Long result = Long.parseLong(String.join("", split));

			ReportDetail reportDetail = new ReportDetail(report, fundingReportDetailRequest.getDescription(),
				result);
			reportDetailRepository.save(reportDetail);
			reportDetails.add(reportDetail);
		}

		report.setReportDetail(reportDetails);
		funding.setPostType(PostType.REPORT_WAIT);
		return FundingReportResponse.from(report);

	}

	@Override
	public FundingReportResponse findFundingReportById(Long fundingId) {
		Report byFundingId = reportRepository.findByFundingFundingId(fundingId);
		FundingReportResponse fundingReport = FundingReportResponse.from(byFundingId);
		return fundingReport;
	}

	@Override
	public FundingReportResponse updateFundingReport(Long fundingId, FundingReportRequest data) {
		Report report = reportRepository.findByFundingFundingId(fundingId);
		Funding funding = fundingRepository.findByFundingId(fundingId).orElseThrow();

		awsS3Uploader.delete("reports/" + fundingId + "/", report.getReceipts().getPath());
		attachRepository.delete(report.getReceipts());
		String receiptUrl = awsS3Uploader.upload(data.getReceiptFile(), "reports/" + fundingId);

		Attach attach = Attach.builder()
			.name(fundingId + "-receiptFile")
			.fileType(FileType.RECEIPT)
			.path(receiptUrl)
			.regDate(LocalDateTime.now())
			.build();

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

		report.setReportReceipts(attach);
		report.setReportContent(data.getContent());
		report.setReportDetail(reportDetails);
		report.setReportRegDate(LocalDateTime.now());
		funding.setPostType(PostType.REPORT_WAIT);

		return FundingReportResponse.from(report);
	}

	@Override
	public void createFundingComment(Long fundingId, FundingCommentRequest data) {
		Funding funding = fundingRepository.findByFundingId(fundingId).orElseThrow();
		Long userId = SecurityUtil.getCurrentUserId();
		Member member = memberRepository.findById(userId).orElseThrow();

		Comment comment = new Comment(funding, member, data.getContent());
		commentRepository.save(comment);
	}

	@Override
	public void deleteFundingComment(Long commentId) throws CommentNotFoundException{
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException());
		commentRepository.delete(comment);
	}

	@Override
	public void takeFunding(Long fundingId, TakeFundingRequest data) {

		Funding funding = fundingRepository.findByFundingId(fundingId).orElseThrow(() -> new IllegalArgumentException());

		Long memberId = SecurityUtil.getCurrentUserId();
		Member member = memberRepository.findById(memberId).orElseThrow();

		String[] split = data.getAmount().split(",");
		Long amount = Long.parseLong(String.join("", split));

		try {
			if (member.getMoney() < amount) {
				throw new InsufficientBalanceException("잔액 부족");
			}

			Payment payment = Payment.builder()
				.amount(amount)
				.post(funding)
				.payDate(LocalDateTime.now())
				.user(member)
				.build();

			paymentRepository.save(payment);

			member.setMoney(member.getMoney() - amount);
			funding.setCurrentFundingAmount(funding.getCurrentFundingAmount() + amount);

			badgeService.postBadges(member, PostGroup.FUNDING);
			badgeService.totalPayAmount(member);


		} catch ( InsufficientBalanceException e) {
			String message = e.getMessage();
			e.printStackTrace();

		}
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
