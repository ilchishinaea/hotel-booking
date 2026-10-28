package com.example.hotelbooking.dto;

import java.time.LocalDate;

public record BookingResponse(
        Long id,
        LocalDate checkInDate,
        LocalDate checkOutDate,
        Long roomId,
        Long userId
) {
}
