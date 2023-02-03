package com.yam.funteer.common.aws;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class S3Controller {

	private final S3Service s3Service;

	@GetMapping("/download/{dir}/{fileName}")
	public ResponseEntity<byte[]> download(@PathVariable String dir,@PathVariable String fileName) throws IOException {
		return s3Service.getObject(dir,fileName);
	}
}