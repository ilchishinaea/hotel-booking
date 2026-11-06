package com.example.hotelbooking.dto;

import java.util.List;

public record BookingListResponse(
        List<BookingResponse> bookings,
        long totalCount
) {
}
