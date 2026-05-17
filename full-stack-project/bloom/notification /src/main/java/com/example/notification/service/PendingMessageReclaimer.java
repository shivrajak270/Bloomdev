package com.example.notification.service;

import com.example.notification.dto.MailDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Range;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.PendingMessage;
import org.springframework.data.redis.connection.stream.PendingMessages;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Service
public class PendingMessageReclaimer {

    private final RedisTemplate<String, String> redisTemplate;


    @Autowired
    private MailService mailService;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${consumer.name}")
    private String consumerName;

    private static final String STREAM = "notification-stream";
    private static final String GROUP = "notification-group";
    private static final String DLQ_STREAM = "notification-dlq-stream";

    // FAST TESTING
    private static final Duration STALE_TIME = Duration.ofSeconds(5);

    // AFTER 5 FAILURES -> DLQ
    private static final int MAX_RETRIES = 5;

    public PendingMessageReclaimer(
            RedisTemplate<String, String> redisTemplate
    ) {
        this.redisTemplate = redisTemplate;
    }

    @Scheduled(fixedDelay = 5000)
    public void reclaimPendingMessages() {

        try {

            System.out.println("\n=================================================");
            System.out.println(" STARTING PENDING MESSAGE SCAN");
            System.out.println("=================================================");


            //takes all the pending messages unbound means all the messages 20 is the limit to balance the load
            PendingMessages pendingMessages =
                    redisTemplate.opsForStream()
                            .pending(
                                    STREAM,
                                    GROUP,
                                    Range.unbounded(),
                                    20
                            );

            System.out.println(" Pending Message Count: "
                    + pendingMessages.size());

            if (pendingMessages.isEmpty()) {

                System.out.println(" No Pending Messages");
                return;
            }

            for (PendingMessage pendingMessage : pendingMessages) {

                try {

                    System.out.println("\n-------------------------------------------------");
                    System.out.println(" PENDING MESSAGE");
                    System.out.println("-------------------------------------------------");

                    System.out.println(" Message ID: "
                            + pendingMessage.getId());

                    System.out.println(" Consumer: "
                            + pendingMessage.getConsumerName());

                    System.out.println(" Idle Time: "
                            + pendingMessage
                            .getElapsedTimeSinceLastDelivery()
                            .toSeconds()
                            + " seconds");

                    System.out.println(" Previous Delivery Count: "
                            + pendingMessage.getTotalDeliveryCount());

                    Duration idleTime =
                            pendingMessage
                                    .getElapsedTimeSinceLastDelivery();

                    // SKIP FRESH MESSAGE like if the message should be old one once delivered
                    if (idleTime.compareTo(STALE_TIME) < 0) {

                        System.out.println(" Message still fresh");
                        continue;
                    }

                    System.out.println(" CLAIMING MESSAGE");

                    //claim the message
                    List<MapRecord<String, Object, Object>> claimedMessages =
                            redisTemplate.opsForStream().claim(
                                    STREAM,
                                    GROUP,
                                    consumerName,
                                    STALE_TIME,
                                    RecordId.of(
                                            pendingMessage.getIdAsString()
                                    )
                            );

                    System.out.println(" Claimed Message Count: "
                            + claimedMessages.size());

                    if (claimedMessages.isEmpty()) {

                        System.out.println("No messages claimed");
                        continue;
                    }

                    for (MapRecord<String, Object, Object> record
                            : claimedMessages) {

                        System.out.println("\n***********************************************");
                        System.out.println("PROCESSING CLAIMED MESSAGE");
                        System.out.println("***********************************************");

                        System.out.println(" Record ID: "
                                + record.getId());

                        Map<Object, Object> value =
                                record.getValue();

                        System.out.println(" Payload: "
                                + value);


                        //process and see
                        boolean success = process(value);

                        // FORCE FAILURE FOR TESTING
//                         success = false;

                        System.out.println(" Process Success: "
                                + success);

                        if (success) {

                            System.out.println(" PROCESS SUCCESS");


                            //send ack
                            Long ack =
                                    redisTemplate.opsForStream()
                                            .acknowledge(
                                                    STREAM,
                                                    GROUP,
                                                    record.getId()
                                            );

                            System.out.println( "ACK RESPONSE: "
                                    + ack);

                        } else {

                            System.out.println(" PROCESS FAILED");

                            // IMPORTANT FIX
                            long deliveryCount =
                                    pendingMessage
                                            .getTotalDeliveryCount() + 1;

                            System.out.println(" Updated Delivery Count: "
                                    + deliveryCount);

                            System.out.println(" Max Retry Limit: "
                                    + MAX_RETRIES);

                            // MOVE TO DLQ
                            if (deliveryCount >= MAX_RETRIES) {

                                System.out.println("💀 MOVING TO DLQ");

                                RecordId dlqId =
                                        redisTemplate.opsForStream()
                                                .add(
                                                        DLQ_STREAM,
                                                        value
                                                );

                                System.out.println(" DLQ Record ID: "
                                        + dlqId);

                                Long ack =
                                        redisTemplate.opsForStream()
                                                .acknowledge(
                                                        STREAM,
                                                        GROUP,
                                                        record.getId()
                                                );

                                System.out.println(" DLQ ACK RESPONSE: "
                                        + ack);

                                System.out.println(" MESSAGE MOVED TO DLQ");

                            } else {

                                System.out.println(" Leaving in PEL");
                                System.out.println(" Will retry later");
                            }
                        }
                    }

                } catch (Exception e) {

                    System.out.println("\n ERROR PROCESSING MESSAGE");
                    e.printStackTrace();
                }
            }

            System.out.println("\n=================================================");
            System.out.println(" PENDING SCAN COMPLETE");
            System.out.println("=================================================\n");

        } catch (Exception e) {

            System.out.println("\n ERROR IN RECLAIMER");
            e.printStackTrace();
        }
    }

    private boolean process(Map<Object, Object> value) {

        try {

            System.out.println(" Processing Payload: "
                    + value);

            // EXTRACT JSON
            String json =
                    String.valueOf(
                            value.get("data")
                    );

            // CONVERT JSON -> DTO
            MailDTO dto =
                    objectMapper.readValue(
                            json,
                            MailDTO.class
                    );

            System.out.println(" Parsed DTO: "
                    + dto);

            // SEND EMAIL
            String response =
                    mailService.sendEmail(dto);

            System.out.println(" Mail Service Response: "
                    + response);

            // SUCCESS
            return "SUCCESS".equalsIgnoreCase(response);

        } catch (Exception e) {

            System.out.println(" PROCESS METHOD FAILED");

            e.printStackTrace();

            return false;
        }

    }
}