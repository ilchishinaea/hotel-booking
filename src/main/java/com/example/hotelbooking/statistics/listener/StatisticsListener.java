package com.example.hotelbooking.statistics.listener;

import com.example.hotelbooking.statistics.config.KafkaTopicConfig;
import com.example.hotelbooking.statistics.event.RoomBookedEvent;
import com.example.hotelbooking.statistics.event.UserRegisteredEvent;
import com.example.hotelbooking.statistics.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StatisticsListener {

    private final StatisticsService statisticsService;

    @KafkaListener(topics = KafkaTopicConfig.USER_REGISTRATION_TOPIC)
    public void onUserRegistered(UserRegisteredEvent event) {
        log.debug("Событие регистрации пользователя {}", event.userId());
        statisticsService.saveUserRegistration(event);
    }

    @KafkaListener(topics = KafkaTopicConfig.ROOM_BOOKING_TOPIC)
    public void onRoomBooked(RoomBookedEvent event) {
        log.debug("Событие бронирования пользователем {}", event.userId());
        statisticsService.saveRoomBooking(event);
    }
}
