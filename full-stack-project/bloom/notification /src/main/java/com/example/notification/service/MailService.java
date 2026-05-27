package com.example.notification.service;

import com.example.notification.dto.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public String sendEmail(MailDTO mailDTO) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDTO.getTo());
        message.setSubject("someone tried to reach you");
        message.setText("the following user "+mailDTO.getUserName()+" wants to reserve  the medicine "+mailDTO.getMedicineName() +"you can contact him by this  email"+mailDTO.getUserEmail());
        try {
            mailSender.send(message);
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "FAILED: " + e.getMessage();
        }

    }
}
