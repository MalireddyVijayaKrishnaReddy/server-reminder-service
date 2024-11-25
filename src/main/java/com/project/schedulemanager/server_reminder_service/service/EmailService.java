package com.project.schedulemanager.server_reminder_service.service;

import com.project.schedulemanager.server_reminder_service.templates.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    JavaMailSender mailService;
    @Value("${spring.mail.username}")
    private String from;


    public void sendEmail(EmailTemplate emailTemplate, String user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(from);
        email.setTo(user);
        email.setSubject(emailTemplate.getSubject());
        email.setText(emailTemplate.getBody());
        mailService.send(email);

    }
}
