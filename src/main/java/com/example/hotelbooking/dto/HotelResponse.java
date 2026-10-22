package com.example.hotelbooking.dto;

import java.math.BigDecimal;

public record HotelResponse(
        Long id,
        String name,
        String advertisementTitle,
        String city,
        String address,
        Double distanceFromCenter,
        BigDecimal rating,
        Integer numberOfRatings
) {
}
