package com.yam.funteer.attach;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.apache.tika.Tika;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtil {
	private static final Tika tika = new Tika();

	private static final List<String> validImgType = Arrays.asList("image/jpeg", "image/pjpeg", "image/png", "image/gif", "image/bmp",
		"image/x-windows-bmp", "image/svg+xml");

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

	public static File downloadFromUrl(String fileUrl) {
		File file = null;

		try {
			URL url = new URL(fileUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			int responseCode = conn.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				String fileName = "";
				String contentType = conn.getContentType();

				fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);


				log.info("Content-Type = {}", contentType);
				log.info("fileName = {} ", fileName);

				file = new File(fileName);

				InputStream is = conn.getInputStream();
				FileOutputStream os = new FileOutputStream(file);

				final int BUFFER_SIZE = 4096;
				int bytesRead;
				byte[] buffer = new byte[BUFFER_SIZE];

				while ((bytesRead = is.read(buffer)) != -1) {
					os.write(buffer, 0, bytesRead);
				}

				os.close();
				is.close();
				log.info("File downloaded");

				return file;
			}
		}catch(IOException e){
			e.printStackTrace();
		}

		return file;
	}
}
