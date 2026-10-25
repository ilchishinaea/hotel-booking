package com.example.hotelbooking.mapper;

import com.example.hotelbooking.dto.UserRequest;
import com.example.hotelbooking.dto.UserResponse;
import com.example.hotelbooking.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequest request);

    UserRequest toRequest(User user);

    UserResponse toResponse(User user);

    @Mapping(target = "id", ignore = true)
    void updateEntity(UserRequest request, @MappingTarget User user);
}
