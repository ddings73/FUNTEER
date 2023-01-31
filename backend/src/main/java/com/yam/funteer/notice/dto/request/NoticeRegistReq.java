package com.yam.funteer.notice.dto.request;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.parameters.P;
import org.springframework.web.multipart.MultipartFile;

import com.yam.funteer.attach.FileType;
import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.common.code.PostGroup;
import com.yam.funteer.common.code.PostType;
import com.yam.funteer.post.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeRegistReq {
	@NotBlank
	private String title;
	@NotBlank
	private String content;
	private List<MultipartFile>files=new ArrayList<>();

	public Post toEntity(){
		return Post.builder()
			.postGroup(PostGroup.ETC)
			.postType(PostType.NOTICE)
			.title(title)
			.content(content)
			.regDate(LocalDateTime.now())
			.build();
	}

	public Post toEntity(Long id){
		return Post.builder()
			.id(id)
			.postGroup(PostGroup.ETC)
			.postType(PostType.NOTICE)
			.title(title)
			.content(content)
			.regDate(LocalDateTime.now())
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
