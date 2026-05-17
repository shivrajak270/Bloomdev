package com.example.notification.controller;


import com.example.notification.dto.MailDTO;
import com.example.notification.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notify")
public class NotificationController {

    @Autowired
    private MailService mailService;


    @PostMapping("/")
    public ResponseEntity<String>sendNotification(@RequestBody MailDTO mailDTO) {
        String reponse=mailService.sendEmail(mailDTO);
        if(reponse.equals("SUCCESS")){
            return ResponseEntity.ok(reponse);
        }
        return ResponseEntity.badRequest().body(reponse);
    }


}
