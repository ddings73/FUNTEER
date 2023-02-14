package com.yam.funteer.exception;

import lombok.Getter;
import org.springframework.dao.DataAccessException;

@Getter
public class SessionNotFoundException extends DataAccessException {
    public SessionNotFoundException(){
        super("세션이 존재하지 않음");
    }
}
