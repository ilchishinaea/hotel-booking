package com.example.hotelbooking.service;

import com.example.hotelbooking.dto.HotelFilter;
import com.example.hotelbooking.dto.HotelListResponse;
import com.example.hotelbooking.dto.HotelRequest;
import com.example.hotelbooking.dto.HotelResponse;
import com.example.hotelbooking.dto.RatingRequest;
import com.example.hotelbooking.entity.Hotel;
import com.example.hotelbooking.mapper.HotelMapper;
import com.example.hotelbooking.repository.HotelRepository;
import com.example.hotelbooking.specification.HotelSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public HotelListResponse findAll(HotelFilter filter) {
        Page<Hotel> page = hotelRepository.findAll(HotelSpecification.withFilter(filter),
                PageRequest.of(filter.pageNumber(), filter.pageSize()));
        return new HotelListResponse(hotelMapper.toResponseList(page.getContent()), page.getTotalElements());
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
    public HotelResponse updateRating(Long id, RatingRequest request) {
        Hotel hotel = getHotel(id);
        hotel.setRating(calculateRating(hotel, request.newMark()));
        hotel.setNumberOfRatings(hotel.getNumberOfRatings() + 1);
        return hotelMapper.toResponse(hotelRepository.save(hotel));
    }

    @Transactional
    public void delete(Long id) {
        hotelRepository.delete(getHotel(id));
    }

    private BigDecimal calculateRating(Hotel hotel, Integer newMark) {
        int numberOfRatings = hotel.getNumberOfRatings();
        if (numberOfRatings == 0) {
            return BigDecimal.valueOf(newMark).setScale(1, RoundingMode.HALF_UP);
        }
        BigDecimal rating = hotel.getRating();
        BigDecimal totalRating = rating.multiply(BigDecimal.valueOf(numberOfRatings))
                .subtract(rating)
                .add(BigDecimal.valueOf(newMark));
        return totalRating.divide(BigDecimal.valueOf(numberOfRatings), 1, RoundingMode.HALF_UP);
    }

    private Hotel getHotel(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Отель с id " + id + " не найден"));
    }
}
