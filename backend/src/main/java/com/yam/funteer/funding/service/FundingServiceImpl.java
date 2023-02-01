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

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yam.funteer.attach.FileType;
import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.attach.repository.AttachRepository;
import com.yam.funteer.common.aws.AwsS3Uploader;
import com.yam.funteer.common.code.TargetMoneyType;
import com.yam.funteer.common.security.SecurityUtil;
import com.yam.funteer.funding.dto.request.FundingCommentRequest;
import com.yam.funteer.funding.dto.request.FundingReportDetailRequest;
import com.yam.funteer.funding.dto.request.TargetMoneyRequest;
import com.yam.funteer.funding.dto.response.CommentResponse;
import com.yam.funteer.funding.dto.response.FundingDetailResponse;
import com.yam.funteer.funding.dto.response.FundingListPageResponse;
import com.yam.funteer.funding.dto.response.FundingListResponse;
import com.yam.funteer.funding.dto.request.FundingReportRequest;
import com.yam.funteer.funding.dto.response.FundingReportResponse;
import com.yam.funteer.funding.dto.request.FundingRequest;
import com.yam.funteer.funding.dto.request.TakeFundingRequest;
import com.yam.funteer.funding.dto.response.HashtagResponse;
import com.yam.funteer.funding.dto.response.TargetMoneyResponse;
import com.yam.funteer.funding.entity.Category;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.entity.Report;
import com.yam.funteer.funding.entity.ReportDetail;
import com.yam.funteer.funding.entity.TargetMoney;
import com.yam.funteer.funding.exception.CommentNotFoundException;
import com.yam.funteer.funding.exception.FundingNotFoundException;
import com.yam.funteer.funding.exception.InsufficientBalanceException;
import com.yam.funteer.funding.exception.NotFoundHashtagException;
import com.yam.funteer.funding.repository.FundingRepository;
import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.funding.repository.ReportDetailRepository;
import com.yam.funteer.funding.repository.ReportRepository;
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


	@Override
	public List<FundingListResponse> findFundingByKeyword(String keyword) {
		List<Funding> byTitleContaining = fundingRepository.findAllByTitleContainingOrContentContaining(keyword, keyword);
		List<FundingListResponse> collect = byTitleContaining.stream()
			.map(funding -> FundingListResponse.from(funding))
			.collect(Collectors.toList());
		return collect;
	}

	@Override
	public List<FundingListResponse> findFundingByHashtag(String hashtag) {
		Long hashtagId = hashTagRepository.findByName(hashtag).get().getId();
		List<PostHashtag> byHashtag = postHashtagRepository.findByHashtagId(hashtagId);
		List<Funding> posts = new ArrayList<>();
		for (PostHashtag postHashtag : byHashtag) {
			Optional<Funding> funding = fundingRepository.findById(postHashtag.getPost().getId());
			posts.add(funding.get());
		}
		List<FundingListResponse> collect = posts.stream()
			.map(funding -> FundingListResponse.from(funding))
			.collect(Collectors.toList());
		return collect;

	}


	@Override
	public List<FundingListResponse> findFundingByCategory(Long categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow();
		List<Funding> allByCategory = fundingRepository.findAllByCategory(category);
		List<FundingListResponse> collect = allByCategory.stream()
			.map(funding -> FundingListResponse.from(funding))
			.collect(Collectors.toList());
		return collect;
	}

	@Override
	public FundingListPageResponse findAllFunding() {
		List<FundingListResponse> collect = fundingRepository.findAll().stream().map(m -> FundingListResponse.from(m)).collect(Collectors.toList());
		List<Funding> successFundingList = fundingRepository.findAllByPostType(PostType.REPORT_ACCEPT);

		int totalFundingCount = collect.size();
		int successFundingCount = successFundingList.size();

		Long totalFundingAmount = 0L;
		for (Funding funding : successFundingList) {
			totalFundingAmount += funding.getCurrentFundingAmount();
		}
		FundingListPageResponse fundingListPageResponse = new FundingListPageResponse(collect, totalFundingCount, successFundingCount, totalFundingAmount);
		return fundingListPageResponse;
	}


	@Override
	public FundingDetailResponse createFunding(MultipartFile thumbnail, FundingRequest data) throws IOException {
		// 인증 완료된 team 아니면 펀딩 작성 못함

		// category 들고오기
		Category category = categoryRepository.findById(data.getCategoryId()).orElseThrow();

		// // Team
		Long currentUserId = SecurityUtil.getCurrentUserId();
		Team team = teamRepository.findById(currentUserId).orElseThrow();

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
			.postGroup(PostGroup.FUNDING)
			.postType(PostType.FUNDING_WAIT)
			.fundingDescription(data.getFundingDescription())
			.build();

		Funding savedPost = fundingRepository.save(funding);

		// s3 변환
		String thumbnailUrl = awsS3Uploader.upload(thumbnail, "thumbnails/" + savedPost.getId());

		savedPost.setThumbnail(thumbnailUrl);

		addTargetMoney(data, funding);

		try {
			if (data.getHashtags() == null) {
				throw new NotFoundHashtagException();
			}
			List<Hashtag> hashtagList = parseHashTags(data.getHashtags());
			List<Hashtag> hashtags = saveNotExistHashTags(hashtagList);
			addPostHashtags(funding, hashtags);

		} catch (NotFoundHashtagException e) {
			e.printStackTrace();
		}

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

		for (TargetMoneyRequest tm : data.getTargetMoneyRequestList()) {
			TargetMoney targetMoney = new TargetMoney(funding, tm.getTargetMoneyType(), tm.getAmount(), tm.getDescription());
			targetMoneyRepository.save(targetMoney);
			targetMoneyList.add(targetMoney);
		}

		funding.setTargetMoneyList(targetMoneyList);
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
	public FundingDetailResponse findFundingById(Long id) {
		Funding funding = fundingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
		FundingDetailResponse fundingDetailResponse = FundingDetailResponse.from(funding);
		long wishCount = wishRepository.countAllByFundingIdAndChecked(id, true);
		fundingDetailResponse.setWishCount(wishCount);

		// 목표금액
		fundingDetailResponse.setTargetMoneyListLevelOne(targetMoneyRepository.findAllByFundingIdAndTargetMoneyType(
			id, TargetMoneyType.LEVEL_ONE));
		fundingDetailResponse.setTargetMoneyListLevelTwo(targetMoneyRepository.findAllByFundingIdAndTargetMoneyType(
			id, TargetMoneyType.LEVEL_TWO));
		fundingDetailResponse.setTargetMoneyListLevelThree(targetMoneyRepository.findAllByFundingIdAndTargetMoneyType(
			id, TargetMoneyType.LEVEL_THREE));

		return fundingDetailResponse;
	}

	@Override
	public FundingDetailResponse updateFunding(Long fundingId, MultipartFile thumbnail, FundingRequest data) throws Exception {
		Funding funding = fundingRepository.findById(fundingId).orElseThrow(() -> new FundingNotFoundException());

		LocalDate endDate = LocalDate.parse(data.getEndDate(),
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		if (funding.getPostType() == PostType.FUNDING_REJECT) {


			// 기존 파일 삭제, 새로운 파일 추가
			awsS3Uploader.delete("thumbnails/" + String.valueOf(fundingId) + "/", funding.getThumbnail());
			String thumbnailUrl = awsS3Uploader.upload(thumbnail, "thumbnails/"+fundingId);

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
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

			funding.setStartDate(startDate);
			funding.setEndDate(endDate);
			funding.setContent(data.getContent());
			funding.setTitle(data.getTitle());
			funding.setCategory(category);
			funding.setThumbnail(thumbnailUrl);
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
		List<TargetMoney> targetMoneyList = funding.getTargetMoneyList();
		for (TargetMoney targetMoney : funding.getTargetMoneyList()) {
			targetMoneyRepository.delete(targetMoney);
		}

		for (TargetMoneyRequest tm : data.getTargetMoneyRequestList()) {
			TargetMoney targetMoney = new TargetMoney(funding, tm.getTargetMoneyType(), tm.getAmount(),
				tm.getDescription());
			targetMoneyRepository.save(targetMoney);
		}

		funding.setTargetMoneyList(targetMoneyList);
	}

	@Override
	public void deleteFunding(Long fundingId) throws FundingNotFoundException {
		Funding funding = fundingRepository.findById(fundingId).orElseThrow(() -> new FundingNotFoundException());
		awsS3Uploader.delete("thumbnails/" + String.valueOf(fundingId) + "/", funding.getThumbnail());
		fundingRepository.delete(funding);
		postRepository.delete(funding);
	}

	@Override
	public FundingReportResponse createFundingReport(Long fundingId, FundingReportRequest data) {
		Funding funding = fundingRepository.findById(fundingId).orElseThrow();
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
			ReportDetail reportDetail = new ReportDetail(report, fundingReportDetailRequest.getDescription(),
				fundingReportDetailRequest.getAmount());
			reportDetailRepository.save(reportDetail);
			reportDetails.add(reportDetail);
		}

		report.setReportDetail(reportDetails);
		funding.setPostType(PostType.REPORT_WAIT);
		return FundingReportResponse.from(report);

	}

	@Override
	public FundingReportResponse findFundingReportById(Long fundingId) {
		Report byFundingId = reportRepository.findByFundingId(fundingId);
		FundingReportResponse fundingReport = FundingReportResponse.from(byFundingId);
		return fundingReport;
	}

	@Override
	public FundingReportResponse updateFundingReport(Long fundingId, FundingReportRequest data) {
		Report report = reportRepository.findByFundingId(fundingId);
		Funding funding = fundingRepository.findById(fundingId).orElseThrow();

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
			ReportDetail reportDetail = new ReportDetail(report, fundingReportDetailRequest.getDescription(),
				fundingReportDetailRequest.getAmount());
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
		Funding funding = fundingRepository.findById(fundingId).orElseThrow();
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

		Funding funding = fundingRepository.findById(fundingId).orElseThrow(() -> new IllegalArgumentException());

		Long memberId = SecurityUtil.getCurrentUserId();
		Member member = memberRepository.findById(memberId).orElseThrow();

		try {
			if (member.getMoney() < data.getAmount()) {
				throw new InsufficientBalanceException("잔액 부족");
			}

			Payment payment = Payment.builder()
				.amount(data.getAmount())
				.post(funding)
				.payDate(LocalDateTime.now())
				.user(member)
				.build();

			paymentRepository.save(payment);

			member.setMoney(member.getMoney() - data.getAmount());
			funding.setCurrentFundingAmount(funding.getCurrentFundingAmount() + data.getAmount());

		} catch ( InsufficientBalanceException e) {
			String message = e.getMessage();
			e.printStackTrace();

		}
	}

	// 자정이 되면 StartDate가 당일인 펀딩들 중 승인 안료된 펀딩을 진행중으로 변경, 펀딩 금액에 따라 완료/실패 여부 판단
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
			if (funding.getCurrentFundingAmount() >= funding.getTargetMoneyList().get(0).getAmount()) {
				funding.setPostType(PostType.FUNDING_COMPLETE);
			} else {
				funding.setPostType(PostType.FUNDING_FAIL);
			}
		}
	}

}
