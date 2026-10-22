package com.example.hotelbooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record HotelRequest(

        @NotBlank(message = "Название отеля не может быть пустым")
        @Size(max = 255, message = "Название отеля не может быть длиннее 255 символов")
        String name,

        @NotBlank(message = "Заголовок объявления не может быть пустым")
        @Size(max = 255, message = "Заголовок объявления не может быть длиннее 255 символов")
        String advertisementTitle,

        @NotBlank(message = "Город не может быть пустым")
        @Size(max = 255, message = "Название города не может быть длиннее 255 символов")
        String city,

        @NotBlank(message = "Адрес не может быть пустым")
        @Size(max = 255, message = "Адрес не может быть длиннее 255 символов")
        String address,

        @NotNull(message = "Расстояние от центра города обязательно")
        @PositiveOrZero(message = "Расстояние от центра города не может быть отрицательным")
        Double distanceFromCenter
) {
}
