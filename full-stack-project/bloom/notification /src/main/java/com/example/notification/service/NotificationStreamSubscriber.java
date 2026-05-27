package com.example.notification.service;

import com.example.notification.dto.MailDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Service;

//@Service
//public class NotificationSubscriber
//        implements MessageListener {
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private MailService mailService;
//
//    @Override
//    public void onMessage(
//            Message message,
//            byte[] pattern) {
//
//        try {
//
//            String channel =
//                    new String(message.getChannel());
//
//            String body =
//                    new String(message.getBody());
//
//            System.out.println("CHANNEL: " + channel);
//            System.out.println("MESSAGE: " + body);
//
//            if(channel.equals("notification-channel")) {
//
//                MailDTO mailDTO =
//                        objectMapper.readValue(
//                                body,
//                                MailDTO.class
//                        );
//
//                mailService.sendEmail(mailDTO);
//            }
//
//            else if(channel.equals("sample-notification")) {
//
//                System.out.println(
//                        "Sample String Message: is this one " + body
//                );
//            }
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//
//    }
//}

@Service
public class NotificationStreamSubscriber
        implements StreamListener<String,
        MapRecord<String, String, String>> {


      @Autowired
    private MailService mailService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void onMessage(
            MapRecord<String, String, String> message
    ) {

        System.out.println("\n================================");
        System.out.println("NOTIFICATION SERVICE HIT");
        System.out.println("MESSAGE ID: " + message.getId());
        System.out.println("RAW MESSAGE: " + message);
        System.out.println("================================");

        try {

            String json =
                    message.getValue().get("data");

            MailDTO dto =
                    objectMapper.readValue(
                            json,
                            MailDTO.class
                    );

            System.out.println(dto);
            System.out.println("JSON: " + json);
            System.out.println("DTO: " + dto);

            System.out.println(
                    "Message Received"
            );

            // SEND EMAIL
            String response =
                    mailService.sendEmail(dto);

            System.out.println(response);

            // ACKNOWLEDGE MESSAGE
            redisTemplate.opsForStream().acknowledge(
                    "notification-stream",
                    "notification-group",
                    message.getId()
            );

            System.out.println(
                    "Message Acknowledged"
            );

        } catch (Exception e) {

            System.out.println("EMAIL PROCESSING FAILED");

            e.printStackTrace();
        }
    }
}