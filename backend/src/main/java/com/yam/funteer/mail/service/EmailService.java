package com.yam.funteer.mail.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.yam.funteer.common.code.PostGroup;

public interface EmailService {
    void sendEmailCodeMessage(String to) throws Exception;
    void sendPostRejectMessage(String to, String rejectReason, PostGroup postGroup);
    void sendTeamRejectMessage(String to, String rejectReason);

    boolean confirmCode(String email, String code);
}
