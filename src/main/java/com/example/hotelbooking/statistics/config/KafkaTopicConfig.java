package com.example.hotelbooking.statistics.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final String USER_REGISTRATION_TOPIC = "user-registration";
    public static final String ROOM_BOOKING_TOPIC = "room-booking";

    @Bean
    public NewTopic userRegistrationTopic() {
        return TopicBuilder.name(USER_REGISTRATION_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic roomBookingTopic() {
        return TopicBuilder.name(ROOM_BOOKING_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
