package com.yam.funteer.exception;

import lombok.Getter;

@Getter
public class EmailDuplicateException extends RuntimeException{
    public EmailDuplicateException(){
        super("이미 등록된 이메일입니다.");
    }
}
