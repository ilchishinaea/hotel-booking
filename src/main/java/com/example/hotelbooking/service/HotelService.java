package com.example.hotelbooking.service;

import com.example.hotelbooking.dto.HotelListResponse;
import com.example.hotelbooking.dto.HotelRequest;
import com.example.hotelbooking.dto.HotelResponse;
import com.example.hotelbooking.entity.Hotel;
import com.example.hotelbooking.mapper.HotelMapper;
import com.example.hotelbooking.repository.HotelRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public HotelResponse findById(Long id) {
        return hotelMapper.toResponse(getHotel(id));
    }

    public HotelListResponse findAll() {
        List<Hotel> hotels = hotelRepository.findAll();
        return new HotelListResponse(hotelMapper.toResponseList(hotels), hotels.size());
    }

    @Transactional
    public HotelResponse create(HotelRequest request) {
        Hotel hotel = hotelMapper.toEntity(request);
        return hotelMapper.toResponse(hotelRepository.save(hotel));
    }

    @Transactional
    public HotelResponse update(Long id, HotelRequest request) {
        Hotel hotel = getHotel(id);
        hotelMapper.updateEntity(request, hotel);
        return hotelMapper.toResponse(hotelRepository.save(hotel));
    }

    @Transactional
    public void delete(Long id) {
        hotelRepository.delete(getHotel(id));
    }

    private Hotel getHotel(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Отель с id " + id + " не найден"));
    }
}
