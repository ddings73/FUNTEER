package com.yam.funteer.exception;

import lombok.Getter;

@Getter
public class DuplicateInfoException extends RuntimeException{

    private String message;
    public DuplicateInfoException(String message){
        this.message = message;
    }
}
