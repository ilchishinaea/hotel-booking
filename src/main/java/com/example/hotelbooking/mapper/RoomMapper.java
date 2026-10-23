package com.example.hotelbooking.mapper;

import com.example.hotelbooking.dto.RoomRequest;
import com.example.hotelbooking.dto.RoomResponse;
import com.example.hotelbooking.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hotel", ignore = true)
    Room toEntity(RoomRequest request);

    @Mapping(target = "hotelId", source = "hotel.id")
    RoomRequest toRequest(Room room);

    @Mapping(target = "hotelId", source = "hotel.id")
    RoomResponse toResponse(Room room);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hotel", ignore = true)
    void updateEntity(RoomRequest request, @MappingTarget Room room);
}
