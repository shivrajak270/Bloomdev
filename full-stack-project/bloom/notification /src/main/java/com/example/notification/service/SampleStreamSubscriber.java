package com.example.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class SampleStreamSubscriber
        implements StreamListener<String,
                MapRecord<String, String, String>> {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void onMessage(
            MapRecord<String, String, String> message
    ) {

        try {

            String data =
                    message.getValue().get("data");

            System.out.println(
                    "Sample Message Received: "
                            + data
            );

            // ACKNOWLEDGE
//            redisTemplate.opsForStream()
//                    .acknowledge(
//                            "sample-group",
//                            message
//                    );

            System.out.println(
                    "Sample Message Acknowledged"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
