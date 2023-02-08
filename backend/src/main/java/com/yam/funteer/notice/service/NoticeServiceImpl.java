package com.yam.funteer.notice.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
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
import com.yam.funteer.faq.dto.response.FaqListRes;
import com.yam.funteer.notice.dto.request.NoticeRegistReq;
import com.yam.funteer.notice.dto.response.NoticeBaseRes;
import com.yam.funteer.notice.exception.NoticeNotFoundException;
import com.yam.funteer.post.entity.Post;
import com.yam.funteer.post.repository.PostRepository;
import com.yam.funteer.user.entity.User;
import com.yam.funteer.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{

	private final PostRepository postRepository;
	private final PostAttachRepository postAttachRepository;
	private final UserRepository userRepository;
	private final AttachRepository attachRepository;

	private final AwsS3Uploader awsS3Uploader;

	@Override
	public List<FaqListRes> noticeGetList(int page,int size) {
		PageRequest pageRequest=PageRequest.of(page,size);
		List<Post>postList=postRepository.findAllByPostTypeOrderByIdDesc(PostType.NOTICE,pageRequest);
		List<FaqListRes>noticeList;
		noticeList=postList.stream().map(notice->new FaqListRes(notice)).collect(Collectors.toList());
		return noticeList;
	}

	@Override
	public NoticeBaseRes noticeGetDetail(Long postId) {
		Post post=postRepository.findById(postId).orElseThrow(()->new NoticeNotFoundException());
		List<PostAttach>postAttachList=postAttachRepository.findAllByPost(post);
		Map<String,String>paths=new HashMap<>();
		for(PostAttach postAttach:postAttachList){
			paths.put(postAttach.getAttach().getName(),postAttach.getAttach().getPath());
		}
		List<Map.Entry<String,String>> pathList=paths.entrySet().stream().collect(Collectors.toList());
		return new NoticeBaseRes(post,pathList);
	}

	@Override
	public NoticeBaseRes noticeRegister(NoticeRegistReq noticeRegistReq) {
		User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());
		List<MultipartFile>files=noticeRegistReq.getFiles();
		if(user.getUserType().equals(UserType.ADMIN)){
			Map<String,String> paths=new HashMap<>();
			Post post=postRepository.save(noticeRegistReq.toEntity());

			if(!files.isEmpty()) {
				for (MultipartFile file : files) {
					if (file.isEmpty())
						break;
					String fileUrl = awsS3Uploader.upload(file, "notice");
					Attach attach = noticeRegistReq.toAttachEntity(fileUrl, file.getOriginalFilename());
					paths.put(file.getOriginalFilename(),fileUrl);
					PostAttach postAttach = PostAttach.builder()
						.attach(attach)
						.post(post)
						.build();
					attachRepository.save(attach);
					postAttachRepository.save(postAttach);
				}
			}
			List<Map.Entry<String,String>> pathList=paths.entrySet().stream().collect(Collectors.toList());
			return new NoticeBaseRes(post,pathList);
		}else throw new IllegalArgumentException("접근권한이 없습니다.");
	}

	@Override
	public NoticeBaseRes noticeModify(Long postId, NoticeRegistReq noticeRegistReq)  {
		Post postOrigin=postRepository.findById(postId).orElseThrow(()->new NoticeNotFoundException());
		User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());
		if(user.getUserType().equals(UserType.ADMIN)) {
			List<PostAttach>postAttachList=postAttachRepository.findAllByPost(postOrigin);
			for(PostAttach postAttach:postAttachList){
				awsS3Uploader.delete("notice/",postAttach.getAttach().getPath());
				postAttachRepository.deleteById(postAttach.getId());
				attachRepository.deleteById(postAttach.getAttach().getId());
			}

			Post post=postRepository.save(noticeRegistReq.toEntity(postId));
			List<MultipartFile>files=noticeRegistReq.getFiles();
			Map<String,String> paths=new HashMap<>();

			if(!files.isEmpty()) {
				for (MultipartFile file : files) {
					if (file.isEmpty())
						break;
					String fileUrl = awsS3Uploader.upload(file, "notice");
					Attach attach = noticeRegistReq.toAttachEntity(fileUrl, file.getOriginalFilename());
					PostAttach postAttach = PostAttach.builder()
						.attach(attach)
						.post(post)
						.build();
					paths.put(file.getOriginalFilename(),fileUrl);
					attachRepository.save(attach);
					postAttachRepository.save(postAttach);

				}
			}
			List<Map.Entry<String,String>> pathList=paths.entrySet().stream().collect(Collectors.toList());
			return new NoticeBaseRes(post,pathList);
		}else throw new IllegalArgumentException("접근 권한이 없습니다.");
	}

	@Override
	public void noticeDelete(Long postId) {
		User user=userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(()->new UserNotFoundException());
		if(user.getUserType().equals(UserType.ADMIN)) {
			Post post = postRepository.findById(postId).orElseThrow(()->new NoticeNotFoundException());
			List<PostAttach>postAttachList=postAttachRepository.findAllByPost(post);
			for(PostAttach postAttach:postAttachList){
				awsS3Uploader.delete("notice/",postAttach.getAttach().getPath());
				postAttachRepository.deleteById(postAttach.getId());
				attachRepository.deleteById(postAttach.getAttach().getId());
			}
			postRepository.delete(post);
		}else throw new IllegalArgumentException("접근권한이 없습니다.");
	}
}
