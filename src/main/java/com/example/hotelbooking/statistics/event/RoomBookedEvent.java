package com.example.hotelbooking.statistics.event;

import java.time.LocalDate;

public record RoomBookedEvent(
        Long userId,
        LocalDate checkInDate,
        LocalDate checkOutDate
) {
}
