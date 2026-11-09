package com.example.hotelbooking.statistics.producer;

import com.example.hotelbooking.statistics.config.KafkaTopicConfig;
import com.example.hotelbooking.statistics.event.RoomBookedEvent;
import com.example.hotelbooking.statistics.event.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class StatisticsProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendUserRegistered(Long userId) {
        kafkaTemplate.send(KafkaTopicConfig.USER_REGISTRATION_TOPIC, new UserRegisteredEvent(userId));
    }

    public void sendRoomBooked(Long userId, LocalDate checkInDate, LocalDate checkOutDate) {
        kafkaTemplate.send(KafkaTopicConfig.ROOM_BOOKING_TOPIC,
                new RoomBookedEvent(userId, checkInDate, checkOutDate));
    }
}
