package com.javayuhm.fastlms.components;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@RequiredArgsConstructor
@Component
public class MailComponents {

    private final JavaMailSender javaMailSender;

    public void sendMailTest() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("kora1492@naver.com");
        simpleMailMessage.setSubject("안녕하세요. ");
        simpleMailMessage.setText("테스트 메일입니다.");

        javaMailSender.send(simpleMailMessage);
    }

    public boolean sendMail(String mail, String subject, String text) {

        boolean result = false;

        MimeMessagePreparator msg = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
                mimeMessageHelper.setTo(mail);
                mimeMessageHelper.setSubject(subject);
                mimeMessageHelper.setText(text, true);


            }
        };

        try {
            javaMailSender.send(msg);
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;

    }
}
