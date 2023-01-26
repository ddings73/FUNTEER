package com.yam.funteer.post.contorller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yam.funteer.common.BaseResponseBody;

@RestController
public class NoticeFaqController {

	@GetMapping("/notice")
	public ResponseEntity<? extends BaseResponseBody>noticeGetList(){


		return null;

	}

	@GetMapping("/faq")
	public ResponseEntity<? extends  BaseResponseBody>faqGetList(){
		return null;
	}

	@PostMapping("/notice")
	public ResponseEntity<? extends BaseResponseBody>noticeRegister(){
		return null;
	}

	@PostMapping("/faq")
	public ResponseEntity<? extends BaseResponseBody>faqRegister(){
		return null;
	}
}
