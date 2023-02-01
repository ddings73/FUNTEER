package com.yam.funteer.qna.dto.request;

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
import com.yam.funteer.qna.entity.Qna;
import com.yam.funteer.user.entity.User;
import com.yam.funteer.user.repository.UserRepository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter @Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class QnaRegisterReq {
	@NotBlank
	private String title;
	@NotBlank
	private String content;

	private List<MultipartFile>files=new ArrayList<>();

	public Qna toEntity(User user){
		return Qna.builder()
			.title(title)
			.content(content)
			.user(user)
			.regDate(LocalDateTime.now())
			.postGroup(PostGroup.ETC)
			.postType(PostType.QNA).build();
	}

	public Qna toEntity(User user,Long postId){
		return Qna.builder()
			.id(postId)
			.title(title)
			.content(content)
			.user(user)
			.regDate(LocalDateTime.now())
			.postGroup(PostGroup.ETC)
			.postType(PostType.QNA).build();
	}

	public Attach toAttachEntity(String path,String name){
		return Attach.builder()
			.path(path).name(name)
			.regDate(LocalDateTime.now())
			.fileType(FileType.OTHER)
			.build();
	}

}
