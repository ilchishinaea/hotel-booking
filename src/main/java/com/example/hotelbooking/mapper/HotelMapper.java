package com.example.hotelbooking.mapper;

import com.example.hotelbooking.dto.HotelRequest;
import com.example.hotelbooking.dto.HotelResponse;
import com.example.hotelbooking.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "numberOfRatings", ignore = true)
    Hotel toEntity(HotelRequest request);

    HotelRequest toRequest(Hotel hotel);

    HotelResponse toResponse(Hotel hotel);

    List<HotelResponse> toResponseList(List<Hotel> hotels);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "numberOfRatings", ignore = true)
    void updateEntity(HotelRequest request, @MappingTarget Hotel hotel);
}
