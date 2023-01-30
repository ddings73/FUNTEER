package com.yam.funteer.qna.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.attach.entity.PostAttach;
import com.yam.funteer.attach.repository.AttachRepository;
import com.yam.funteer.attach.repository.PostAttachRepository;
import com.yam.funteer.common.aws.AwsS3Uploader;
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.common.code.UserType;
import com.yam.funteer.common.security.SecurityUtil;
import com.yam.funteer.exception.UserNotFoundException;

import com.yam.funteer.funding.dto.FundingListResponse;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.qna.dto.request.QnaRegisterReq;
import com.yam.funteer.qna.dto.response.QnaBaseRes;
import com.yam.funteer.qna.dto.response.QnaListRes;
import com.yam.funteer.qna.entity.Qna;
import com.yam.funteer.qna.entity.Reply;
import com.yam.funteer.qna.exception.QnaNotFoundException;
import com.yam.funteer.qna.repository.QnaRepository;
import com.yam.funteer.qna.repository.ReplyRepository;

import com.yam.funteer.user.entity.User;
import com.yam.funteer.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService {

	private final QnaRepository qnaRepository;
	private final UserRepository userRepository;
	private final ReplyRepository replyRepository;
	private final PostAttachRepository postAttachRepository;
	private final AttachRepository attachRepository;

	private final AwsS3Uploader awsS3Uploader;

	@Override
	public List<QnaListRes> qnaGetList() {
		User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());
		List<QnaListRes>list;

		if(user.getUserType().equals(UserType.ADMIN)){
			List<Qna>qnaList=qnaRepository.findAll();
			list=qnaList.stream().map(qna->new QnaListRes(qna)).collect(Collectors.toList());

			return list;
		}

		List<Qna>qnaList=qnaRepository.findAllByUser(user);
		list=qnaList.stream().map(qna->new QnaListRes(qna)).collect(Collectors.toList());

		return list;
	}

	@Override
	public QnaBaseRes qnaRegister(QnaRegisterReq qnaRegisterReq,List<MultipartFile>files){
		User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());
		Qna qna=qnaRepository.save(qnaRegisterReq.toEntity(user));
		List<String>attachList=new ArrayList<>();
		for(MultipartFile file:files){
			String fileUrl = awsS3Uploader.upload(file,"/qna");
			Attach attach=qnaRegisterReq.toAttachEntity(fileUrl,file.getOriginalFilename());
			PostAttach postAttach=PostAttach.builder()
				.attach(attach)
				.post(qna)
				.build();
			attachList.add(fileUrl);
			attachRepository.save(attach);
			postAttachRepository.save(postAttach);
		}return new QnaBaseRes(qna,attachList);
	}


	@Override
	public QnaBaseRes qnaGetDetail(Long qnaId) throws QnaNotFoundException {
		Qna qna = qnaRepository.findById(qnaId).orElseThrow(() -> new QnaNotFoundException());
		User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());
		if (qna.getUser().getId()==user.getId()||user.getUserType().equals(UserType.ADMIN)) {
			List<PostAttach>postAttachList=postAttachRepository.findAllByPost(qna);
			List<String>attachList=new ArrayList<>();
			if(postAttachList.size()>0) {
				for (PostAttach postAttach : postAttachList) {
					attachList.add(postAttach.getAttach().getPath());
				}
			}
			return new QnaBaseRes(qna,attachList);
		}
		else throw new IllegalArgumentException("접근권한이 없습니다.");
	}

	@Override
	public QnaBaseRes qnaModify(Long qnaId, QnaRegisterReq qnaRegisterReq, List<MultipartFile>files) throws
		QnaNotFoundException{
		Qna qna = qnaRepository.findById(qnaId).orElseThrow(() -> new QnaNotFoundException());
		User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());
		List<String>attachList=new ArrayList<>();
		if(user.getId()==qna.getUser().getId()) {
			qnaRepository.save(qnaRegisterReq.toEntity(user,qnaId));
			List<PostAttach>postAttachList=postAttachRepository.findAllByPost(qna);
			for(PostAttach postAttach:postAttachList){
				awsS3Uploader.delete("/qna/",postAttach.getAttach().getPath());
				postAttachRepository.deleteById(postAttach.getId());
				attachRepository.deleteById(postAttach.getAttach().getId());
			}

			for(MultipartFile file:files){
				String fileUrl = awsS3Uploader.upload(file,"/qna");
				Attach attach=qnaRegisterReq.toAttachEntity(fileUrl,file.getOriginalFilename());
				PostAttach postAttach=PostAttach.builder()
					.attach(attach)
					.post(qna)
					.build();
				attachList.add(fileUrl);
				attachRepository.save(attach);
				postAttachRepository.save(postAttach);
			}
		}
		return new QnaBaseRes(qna,attachList);
	}

	@Override
	public void qnaDelete(Long postId) throws QnaNotFoundException{
		Qna qna = qnaRepository.findById(postId).orElseThrow(() -> new QnaNotFoundException());
		User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());
		if(qna.getUser().getId()==user.getId()) {
			List<PostAttach>postAttachList=postAttachRepository.findAllByPost(qna);
			for(PostAttach postAttach:postAttachList){
				awsS3Uploader.delete("/qna/",postAttach.getAttach().getPath());
				postAttachRepository.deleteById(postAttach.getId());
				attachRepository.deleteById(postAttach.getAttach().getId());
			}

			qnaRepository.delete(qna);
			Reply reply=replyRepository.findByQna(qna);
			if(reply!=null){
				replyRepository.delete(reply);
		}
		}
	}
}
