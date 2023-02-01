package com.yam.funteer.badge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yam.funteer.badge.dto.request.BadgeRegisterReq;
import com.yam.funteer.badge.service.BadgeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/badge")
public class BadgeController {

	private final BadgeService badgeService;


	@PostMapping("")
	public ResponseEntity<?>badgeRegister(@RequestBody BadgeRegisterReq badgeRegisterReq){
		return ResponseEntity.ok(badgeService.badgeRegister(badgeRegisterReq));
	}


}
