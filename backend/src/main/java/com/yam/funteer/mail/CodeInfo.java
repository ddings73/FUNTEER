package com.yam.funteer.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@AllArgsConstructor
public class CodeInfo {
    private String code;
    private LocalDateTime generatedDate;

    public boolean expired() {
        long minute = ChronoUnit.MINUTES.between(this.generatedDate, LocalDateTime.now());
        return minute >= 3;
    }

    public boolean validateCode(String code) {
        return this.code.equals(code);
    }
}
