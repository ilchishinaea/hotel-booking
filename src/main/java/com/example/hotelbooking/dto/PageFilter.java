package com.example.hotelbooking.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record PageFilter(

        @Min(value = 1, message = "Количество записей на странице не может быть меньше 1")
        @Max(value = 100, message = "Количество записей на странице не может быть больше 100")
        Integer pageSize,

        @Min(value = 0, message = "Номер страницы не может быть отрицательным")
        Integer pageNumber
) {

    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_PAGE_NUMBER = 0;

    public PageFilter {
        if (pageSize == null) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        if (pageNumber == null) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
    }
}
