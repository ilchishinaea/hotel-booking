package com.example.hotelbooking.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookingRequest(

        @NotNull(message = "Дата заезда обязательна")
        LocalDate checkInDate,

        @NotNull(message = "Дата выезда обязательна")
        LocalDate checkOutDate,

        @NotNull(message = "Идентификатор комнаты обязателен")
        Long roomId,

        @NotNull(message = "Идентификатор пользователя обязателен")
        Long userId
) {
}
