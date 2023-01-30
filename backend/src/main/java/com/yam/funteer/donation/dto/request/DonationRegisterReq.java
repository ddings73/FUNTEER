package com.yam.funteer.donation.dto.request;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yam.funteer.attach.FileType;
import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.donation.entity.Donation;
import com.yam.funteer.user.entity.Member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonationRegisterReq {
	private String content;
	private String title;
	private Long amount;

	public Donation toEntity(){
		return Donation.builder()
			.regDate(LocalDateTime.now())
			.postGroup(PostGroup.DONATION)
			.postType(PostType.DONATION_ACTIVE)
			.title(title)
			.content(content)
			.amount(amount)
			.build();
	}

	public Donation toEntity(Long postId){
		return Donation.builder()
			.id(postId)
			.regDate(LocalDateTime.now())
			.postGroup(PostGroup.DONATION)
			.postType(PostType.DONATION_ACTIVE)
			.title(title)
			.content(content)
			.amount(amount)
			.build();
	}

	public Attach toAttachEntity(String path,String name){
		return Attach.builder()
			.fileType(FileType.OTHER)
			.regDate(LocalDateTime.now())
			.path(path)
			.name(name).build();
	}

}
