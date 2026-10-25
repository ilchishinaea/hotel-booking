package com.example.hotelbooking.dto;

import com.example.hotelbooking.entity.Role;

public record UserResponse(
        Long id,
        String username,
        String email,
        Role role
) {
}
