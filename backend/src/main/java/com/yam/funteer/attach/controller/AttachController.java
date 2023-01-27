package com.yam.funteer.attach.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yam.funteer.common.aws.AwsS3Uploader;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AttachController {
	private final AwsS3Uploader awsS3Uploader;

	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
		String fileName = awsS3Uploader.upload(multipartFile, "test");
		return fileName;
	}
}