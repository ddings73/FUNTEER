package com.yam.funteer.mail.service;

import com.yam.funteer.mail.CodeInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import com.yam.funteer.common.code.PostGroup;

@Service @Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private final Map<String, CodeInfo> mapCodes = new ConcurrentHashMap<>();
    private final JavaMailSender emailSender;
    public static final String ePw = createKey();

    @Override
    public void sendEmailCodeMessage(String to) throws Exception {
        MimeMessage message = createMessage(to);
        try{//예외처리
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void sendPostRejectMessage(String to, String rejectReason, PostGroup postGroup)  {

        try{//예외처리
            MimeMessage message = createFundingMessage(to, rejectReason, postGroup);
            emailSender.send(message);
        } catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void sendTeamRejectMessage(String to, String rejectReason){
        try{//예외처리
            MimeMessage message = createTeamMessage(to, rejectReason);
            emailSender.send(message);
        } catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean confirmCode(String email, String code) {
        if(mapCodes.containsKey(email)){
            CodeInfo codeInfo = mapCodes.get(email);
            mapCodes.remove(email);
            if (codeInfo.expired() || !codeInfo.validateCode(code)){
                return false;
            }
            return true;
        }
        return false;
    }

    private MimeMessage createMessage(String to)throws Exception{
        log.info("보내는 대상 : {}", to);
        log.info("인증 번호 : {}", ePw);
        mapCodes.put(to, new CodeInfo(ePw, LocalDateTime.now()));
        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to);//보내는 대상
        message.setSubject("FUNTEER 인증메일");//제목

        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= "<h1> 안녕하세요 FUNTEER입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>아래 코드를 복사해 입력해주세요<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다.<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= ePw+"</strong><div><br/> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("yamyambuk04@gmail.com","funteer"));//보내는 사람

        return message;
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }

    private MimeMessage createFundingMessage(String to, String rejectReason, PostGroup postGroup) throws
        MessagingException, UnsupportedEncodingException {
        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to);//보내는 대상
        if (postGroup == PostGroup.FUNDING) {
            message.setSubject("FUNTEER : 펀딩 승인이 거절되었습니다.");//제목
        } else if (postGroup == PostGroup.REPORT) {
            message.setSubject("FUNTEER : 보고서 승인이 거절되었습니다.");
        }

        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= "<h1> 안녕하세요 FUNTEER입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>다음과 같은 이유로 승인이 거절되었습니다.<p>";
        msgg+= "<br>";
        msgg+= "<p>다시 제출해주시길 바랍니다. 감사합니다.<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';><br/>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= rejectReason+"<div><br/> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("yamyambuk04@gmail.com","funteer"));//보내는 사람

        return message;
    }


    private MimeMessage createTeamMessage(String to, String rejectReason) throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to);//보내는 대상
        message.setSubject("FUNTEER : 단체 가입이 거절되었습니다.");//제목


        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= "<h1> 안녕하세요 FUNTEER입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>다음과 같은 이유로 승인이 거절되었습니다.<p>";
        msgg+= "<br>";
        msgg+= "<p>다시 제출해주시길 바랍니다. 감사합니다.<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';><br/>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= rejectReason+"<div><br/> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("yamyambuk04@gmail.com","funteer"));//보내는 사람

        return message;
    }
}
