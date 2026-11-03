package com.example.hotelbooking.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RatingRequest(

        @NotNull(message = "Оценка обязательна")
        @Min(value = 1, message = "Оценка не может быть меньше 1")
        @Max(value = 5, message = "Оценка не может быть больше 5")
        Integer newMark
) {
}
