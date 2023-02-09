package com.yam.funteer.mail.service;

import com.yam.funteer.common.code.PostGroup;

public interface EmailService {
    void sendEmailCodeMessage(String to) throws Exception;

    String sendPostRejectMessage(String to, String rejectReason, PostGroup postGroup) throws Exception;
    void sendTeamRejectMessage(String to, String rejectReason);

    boolean confirmCode(String email, String code);
}
