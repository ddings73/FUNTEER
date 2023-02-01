package com.yam.funteer.donation.dto.request;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.yam.funteer.attach.FileType;
import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.donation.entity.Donation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DonationModifyReq {
	@NotBlank
	private String content;
	@NotBlank
	private String title;
	@NotNull
	private Long amount;

	private List<MultipartFile>files=new ArrayList<>();

	private PostType postType;

	public Donation toEntity(Long postId){
		return Donation.builder()
			.id(postId)
			.regDate(LocalDateTime.now())
			.postGroup(PostGroup.DONATION)
			.postType(postType)
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
