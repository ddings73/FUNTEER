package com.yam.funteer.common.aws;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class S3Service {

	private final AmazonS3 amazonS3;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	/**
	 * S3 bucket 파일 다운로드
	 */
	public ResponseEntity<byte[]> getObject(String storedFileName) throws IOException {
		System.out.println(bucket);
		System.out.println();
		S3Object o = amazonS3.getObject(new GetObjectRequest(bucket, storedFileName));
		S3ObjectInputStream objectInputStream = ((S3Object) o).getObjectContent();
		byte[] bytes = IOUtils.toByteArray(objectInputStream);

		String fileName = URLEncoder.encode(storedFileName, "UTF-8").replaceAll("\\+", "%20");
		System.out.println(fileName);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.IMAGE_JPEG);
		httpHeaders.setContentLength(bytes.length);
		httpHeaders.setContentDispositionFormData("attachment", fileName);

		return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);

	}
}
