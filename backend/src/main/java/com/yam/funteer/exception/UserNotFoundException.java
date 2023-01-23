package com.yam.funteer.exception;


import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("찾을 수 없는 회원정보 입니다.");
    }
}
