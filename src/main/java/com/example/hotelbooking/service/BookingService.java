package com.example.hotelbooking.service;

import com.example.hotelbooking.dto.BookingRequest;
import com.example.hotelbooking.dto.BookingResponse;
import com.example.hotelbooking.entity.Booking;
import com.example.hotelbooking.entity.Room;
import com.example.hotelbooking.entity.User;
import com.example.hotelbooking.mapper.BookingMapper;
import com.example.hotelbooking.repository.BookingRepository;
import com.example.hotelbooking.repository.RoomRepository;
import com.example.hotelbooking.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final BookingMapper bookingMapper;

    public List<BookingResponse> findAll() {
        return bookingMapper.toResponseList(bookingRepository.findAll());
    }

    @Transactional
    public BookingResponse book(BookingRequest request) {
        if (!request.checkOutDate().isAfter(request.checkInDate())) {
            throw new IllegalArgumentException("Дата выезда должна быть позже даты заезда");
        }

        Room room = getRoom(request.roomId());
        User user = getUser(request.userId());

        if (isRoomTaken(room.getId(), request)) {
            throw new IllegalArgumentException("Комната уже забронирована на выбранные даты");
        }

        Booking booking = bookingMapper.toEntity(request);
        booking.setRoom(room);
        booking.setUser(user);
        return bookingMapper.toResponse(bookingRepository.save(booking));
    }

    private boolean isRoomTaken(Long roomId, BookingRequest request) {
        return bookingRepository.existsByRoomIdAndCheckInDateLessThanAndCheckOutDateGreaterThan(
                roomId, request.checkOutDate(), request.checkInDate());
    }

    private Room getRoom(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Комната с id " + id + " не найдена"));
    }

    private User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с id " + id + " не найден"));
    }
}
