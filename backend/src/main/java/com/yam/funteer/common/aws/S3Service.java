package com.yam.funteer.common.aws;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.tika.Tika;
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
import com.yam.funteer.attach.FileUtil;
import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.attach.repository.AttachRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class S3Service {

	private final AmazonS3 amazonS3;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	private static final Tika tika = new Tika();

	private final AttachRepository attachRepository;

	/**
	 * S3 bucket 파일 다운로드
	 */
	public ResponseEntity<byte[]> getObject(String dir,String storedFileName) throws IOException {

		S3Object o = amazonS3.getObject(new GetObjectRequest(bucket+"/"+dir, storedFileName));
		S3ObjectInputStream objectInputStream = ((S3Object) o).getObjectContent();
		byte[] bytes = IOUtils.toByteArray(objectInputStream);

		String mimeType = tika.detect(objectInputStream);

		String fileName = URLEncoder.encode(storedFileName, "UTF-8").replaceAll("\\+", "%20");
		System.out.println(fileName);
		Attach attach=attachRepository.findByPathEndingWith(fileName);
		String downloadName=URLEncoder.encode(attach.getName(), "UTF-8").replaceAll("\\+", "%20");
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.parseMediaType(mimeType));
		httpHeaders.setContentLength(bytes.length);
		httpHeaders.setContentDispositionFormData("attachment", downloadName);

		return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);

	}
}
