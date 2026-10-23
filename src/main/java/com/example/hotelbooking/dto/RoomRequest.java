package com.example.hotelbooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public record RoomRequest(

        @NotBlank(message = "Название комнаты не может быть пустым")
        @Size(max = 255, message = "Название комнаты не может быть длиннее 255 символов")
        String name,

        @NotBlank(message = "Описание комнаты не может быть пустым")
        @Size(max = 1000, message = "Описание комнаты не может быть длиннее 1000 символов")
        String description,

        @NotNull(message = "Номер комнаты обязателен")
        @Positive(message = "Номер комнаты должен быть положительным")
        Integer number,

        @NotNull(message = "Цена обязательна")
        @Positive(message = "Цена должна быть положительной")
        BigDecimal price,

        @NotNull(message = "Максимальное количество людей обязательно")
        @Positive(message = "Максимальное количество людей должно быть положительным")
        Integer maxGuests,

        Set<LocalDate> unavailableDates,

        @NotNull(message = "Идентификатор отеля обязателен")
        Long hotelId
) {
}
