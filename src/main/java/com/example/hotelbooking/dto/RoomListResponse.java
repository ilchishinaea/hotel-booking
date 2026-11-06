package com.example.hotelbooking.dto;

import java.util.List;

public record RoomListResponse(
        List<RoomResponse> rooms,
        long totalCount
) {
}
