package com.example.hotelbooking.dto;

import java.util.List;

public record HotelListResponse(
        List<HotelResponse> hotels,
        long totalCount
) {
}
