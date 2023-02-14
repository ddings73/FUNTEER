package com.yam.funteer.exception;

import lombok.Getter;

@Getter
public class SessionNotFoundException extends RuntimeException{
    public SessionNotFoundException(){super();}
}
