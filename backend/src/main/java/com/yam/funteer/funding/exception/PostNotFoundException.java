package com.yam.funteer.funding.exception;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public class PostNotFoundException extends Exception {
	public PostNotFoundException() {
	}

	public PostNotFoundException(String s) {
		super(s);
	}
}
