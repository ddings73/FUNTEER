package com.yam.funteer.mail.service;

import com.yam.funteer.common.code.PostGroup;

public interface EmailService {
    String sendSimpleMessage(String to) throws Exception;

    String sendRejectMessage(String to, String rejectReason, PostGroup postGroup) throws Exception;
}
