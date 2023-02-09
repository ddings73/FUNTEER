package com.yam.funteer.attach;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtil {
	private static final Tika tika = new Tika();

	private static final List<String> validImgType = Arrays.asList("image/jpeg", "image/pjpeg", "image/png", "image/gif", "image/bmp",
		"image/x-windows-bmp");

	private static final List<String> validPdfType = Arrays.asList("application/pdf");
	public static boolean validImgFile(InputStream inputStream){
		try {
			String mimeType = tika.detect(inputStream);

			log.info("mimeType => {}", mimeType);

			return validImgType.stream().anyMatch(type->type.equalsIgnoreCase(mimeType));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean validPdfFile(InputStream inputStream){
		try {
			String mimeType = tika.detect(inputStream);

			log.info("mimeType => {}", mimeType);

			return validPdfType.stream().anyMatch(type->type.equalsIgnoreCase(mimeType));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
