package com.example.notification.configurations;

import com.example.notification.service.NotificationStreamSubscriber;
import com.example.notification.service.SampleStreamSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class RedisStreamConfig {

    @Value("${consumer.name}")
    private String consumerName;

    @Bean
    public StreamMessageListenerContainer<String,
                MapRecord<String, String, String>>
    streamMessageListenerContainer(
            RedisConnectionFactory connectionFactory,
            NotificationStreamSubscriber subscriber,
            SampleStreamSubscriber sampleSubscriber
    ) {

        StreamMessageListenerContainer.StreamMessageListenerContainerOptions
                <String, MapRecord<String, String, String>> options =

                StreamMessageListenerContainer
                        .StreamMessageListenerContainerOptions
                        .builder()
                        .pollTimeout(Duration.ofSeconds(1))
                        .build();

        StreamMessageListenerContainer<String,
                MapRecord<String, String, String>> container =

                StreamMessageListenerContainer.create(
                        connectionFactory,
                        options
                );

        container.receive(
                Consumer.from(
                        "notification-group",
                        consumerName
                ),

                StreamOffset.create(
                        "notification-stream",
                        ReadOffset.lastConsumed()
                ),

                subscriber
        );

        container.receive(
                Consumer.from(
                        "sample-group",
                        consumerName
                ),

                StreamOffset.create(
                        "sample-stream",
                        ReadOffset.lastConsumed()
                ),

                sampleSubscriber
        );

        container.start();

        return container;
    }
}