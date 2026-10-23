package com.example.hotelbooking.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public record RoomResponse(
        Long id,
        String name,
        String description,
        Integer number,
        BigDecimal price,
        Integer maxGuests,
        Set<LocalDate> unavailableDates,
        Long hotelId
) {
}
