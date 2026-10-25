package com.example.hotelbooking.dto;

import com.example.hotelbooking.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(

        @NotBlank(message = "Имя пользователя не может быть пустым")
        @Size(max = 255, message = "Имя пользователя не может быть длиннее 255 символов")
        String username,

        @NotBlank(message = "Пароль не может быть пустым")
        @Size(max = 255, message = "Пароль не может быть длиннее 255 символов")
        String password,

        @NotBlank(message = "Электронная почта не может быть пустой")
        @Email(message = "Электронная почта имеет неверный формат")
        @Size(max = 255, message = "Электронная почта не может быть длиннее 255 символов")
        String email,

        @NotNull(message = "Роль пользователя обязательна")
        Role role
) {
}
