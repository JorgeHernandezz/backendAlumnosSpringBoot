package com.ipn.mx.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;
    
    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(new InternetAddress("noreply@gmail.com", "titulo"));
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}